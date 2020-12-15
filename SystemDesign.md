System design

#### Scalability 

video: https://www.youtube.com/watch?v=-W9F__D3oY4

slides: http://cdn.cs75.net/2011/summer/lectures/9/lecture9.pdf

Q: What if website faces a surge of requests

##### Ways of scaling

- Vertical scaling: 
  - upgrade the ability of physical machine (RAM, CPU, storage)
  - drawback: due to real world constraints (financial or technology constraints), there is a limit that the machine you can upgrade to
- Horizontal scaling: add more cheaper servers in to the resource pool

##### Load balancer

![Outbound traffic with Standard Load Balancer Microsoft Azure | Marius Sandbu](https://msandbu.org/wp-content/uploads/2019/08/AWS-ELB-diagram.jpg)

Helps to distribute requests from users.

Step 1: which IP address return to user? 

- Without load balancer: return IP address of specific server (e.g. 1, 2, 3)
- With load balancer: return IP address of load balancer. Load balancer can route data to servers. Servers can have private IP address, which cannot be connected from random hosts.

Step 2: how load balancer determine which server to process data?

- Dedicated servers for images, gifs, videos, by recognize different urls
- Round robin: client request is forwarded to each server in turn
  - Drawback: one server might only get heavy requests
- Randomness
- Depends on load of servers

When we type the url in the browser, what happens? 

OS will send a packet to **DNS server**(domain name system server) that translate host names to IP addresses.

What happens if we repeat the above process?

There is a cache to store the map of hostname and IPs so that OS doesn't need to send same request to DNS servers. Both OS and browser maintain such a cache. Notice caching can contribute to a disproportionate amount of loads due to bad luck. **Time-to-live values** (TTL) suggests that how long to cache this mapping relationship. When TTL expires, the OS gets a new mapping from DNS servers.

Problem: Image the website has multiple backend servers, each server holds their own session. By round-robin algorithm one user requests are distributed to different servers. Therefore users operation are not correctly stored. How to make sure one user request only send to a specific server?

**share session state**: user an addition server to store session date. This server is connected to all other servers. Yet this solution sacrifices some robustness.

##### RAID

Problem: how to fix the problem that the server stores session breaks?

Instead of store on hardware system, we could use **RAID** (redundant array of independent disks). All variants of RAID assume the computer has multiple hard drives.

Types of RAID:

- RAID 0: **Strip** data across 2 hard drives. When OS saves a big files, it first write to one, then the other, then one... which improves the speed for writing
- RAID 1: **Mirror** data on 2 drives. When OS saves a file, it saves to both places.
- RAID 5: Usually more than 3 drives, use one of them for redundancy.
- RAID 6: 
- RAID 10: combination of RAID 0 and 1.

RAID helps to improve the uptime and robustness of single server.

Caching: 

- Problem is if you cache something, but changes it later, which does not reflect.
- memcached: store info in RAM
- garbage collection: throw away least used objects, update the timestamp 

##### Data replication

**Master/slave architecture**: master database is where user read/write data. One master database has multiple slave databases, whose goals is to copy every row in the master db. In theory master and slave db are identical. 

Advantage: 

- when master db is down, simply use any of slave db.
- when face a surge of queries, load balancer can distribute load across multiple servers
- suitable for cases when more read heavy and less write heavy, e.g. insert/delete/update operation goes to server 4, and search goes to server 2/3/4

Disadvanatge:

- the server for updating might die

  ![1606933367556](C:/Users/wenyu/AppData/Roaming/Typora/typora-user-images/1606933367556.png)

**Master/master architecture**: two master databases for updating operations. When write to server 1, the query gets replicated on server 2.

![1606933399531](C:/Users/wenyu/AppData/Roaming/Typora/typora-user-images/1606933399531.png)

**Active/active mode**: have two servers that are actively listening to connections, either one of which can receive packets and send to backend servers. Two load balancers send heartbeat message to each other to check if the other is active.

![active_active_high_availability_cluster_load_balancer](https://www.jscape.com/hubfs/images/active_active_high_availability_cluster_load_balancer.png)

**Active/passive mode**: one server in active state and one in passive state. If the active server dies, the passive server will be promoted as active.

![active_passive_high_availability_cluster](https://www.jscape.com/hubfs/images/active_passive_high_availability_cluster.png)

##### Partitioning



![1606933480468](C:/Users/wenyu/AppData/Roaming/Typora/typora-user-images/1606933480468.png)

Example: how to design a sticky session

![1606935151167](C:/Users/wenyu/AppData/Roaming/Typora/typora-user-images/1606935151167.png)

### Scalability

How does load balancer works?

The end-IP is not published. The process actually works in a way the client (a user hitting the balancer) believes they are communicating with the balancer, while talking to an actual node.

In a very simple explanation, most transactions work like this:

1. A user makes request to the load balancer.
2. The balancer decides which node is the most suitable (based on the strategy you're using for balancing) and choses (changes) the destination IP.
3. (This is where the magic happens.) The node receives a request, accepts the connection and responds back to the balancer.
4. The balancer changes the response IP back to a virtual one, the one of the balancer and forwards the response to the user.
5. Voilà, the user receives response with the IP of the initial request, even though it was actually processed somewhere else.

Keep in mind the packet rewriting (the change of the IP address in the step 4) is very important. Without it the client, receiving a packet from an IP it does not trust, would simply discard the response.

A **cache** is a simple key-value store and it should reside as a buffering layer between your application and your data storage. Whenever your application has to read data it should at first try to retrieve the data from your cache. Only if it’s not in the cache should it then try to get the data from the main data source.

There are 2 patterns of caching your data.

- Cached Database Queries

  Store database result in cache. The key of cache is the hashed version of query.

  Problems: 

  - It is hard to delete a cached result when you cache a complex query
  - When one piece of data changes, need to delete all cached queries

- Cached Objects

  Store the data as an object in cache.

 There are 2 ways of **asynchronism**

- Do the time-consuming work in advance and serving the finished work with a low request time

  e.g. turn dynamic content into static content.

- 

### Cases

#### Design a url shortening service

**Step 1: constraints and use cases**

Use cases:

- url shortening: take a url -> return a shorter url
- redirection: take a short url -> return the original url
- high availability

Out of scope

- customer url: allow users to pick their shortened url
- analytics: user look at analytics
- manual link removal: remove a link that previously shortened
- UI vs API: a website or just API?

Constraints: usually we think of two questions

- amount of traffic the system should handle
- amount of data the system store

You should ask how many requests per month/second the system should handle? How many new URLs per month should site handle?

Interviewer -> top 10 url shortening service

15 billion new tweets /month

1.5 billion URLs per month, 

Sites below the top 3: shorten 300M per month

We: 

1. 100 million new URLs per month

2. 1 billion requests per month

3. 10% from shortening and 90% from redirection

4. request per second: 1b/30 days/24 hours = 400+requests/second (40: shorten, 360: redirect)

5. What data need to store? original url, and its shortened hash

   Totals url need to store: 5 years* 12 months * 100 million = 6 billion urls

6. Average length of url: 500 bytes per URL

7. How big the hash need to handle 6B urls? log base62  = 6B, 6 bytes per hash

8. In total: total storage of URL: 500 bytes * 6 B = 3T

   toal storage of hash: 6 byte * 6B = 36GB

9. New data written per second: 40*(500+6 bytes) = 20K

10. Data read per second: 360 * 506 bytes = 180K

**Step 2: Abstract design**

1. Application service layer: serve requests

   1. shortening service

      Generate hash -> check if in db

      if yes, keep generate a new hash until an unused one

      if no, store a new mapping

   2. redirection service

2. Data storage layer: keep track of url mapping

   Acts like a big hash table: store new mappings, retrieves a value given a key

How hash works?

Apply well-unknown hash algorithm, e.g. md5(一种哈希算法，输入一串字符串，输出为128bit string)

hashed_url = convert_to_62( md5(original_url + random_salt)) -> take first 6 chars

**Step 3: Understanding bottlenecks**

Bottlenects: traffic is probably not going to very hard, data might be problemlistic

**Step 4: Scaling your abstract design**

#### Design a web crawler

**Step 1**: use cases and constraints

Use cases:

- urls -> get title and snippets
- urls -> reverse index of words
- search term -> relevant pages
- high availability

Assumptions

- 1 billions links to crawl
- page refresh about one per week -> 4 billions links to crawl per month
- Average stored size per web page: 500 KB
- 100 billion searches per month

Calculation:

- traffic
  - 1600 write requests / sec = 4 billion per month
  - 40,000 search requests / sec = 100 billion search per month
- data (KB -> MB -> GB -> TB -> PB)
  - 2PB = 1 billion * 500 kb per month
  - 72 PB in 3 years

**Step 2: high level design**

