System design

#### Scalability 

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

Master/slave architecture: master database is where user read/write data. One master database has multiple slave databases, whose goals is to copy every row in the master db. In theory master and slave db are identical. 

Advantage: 

- when master db is down, simply use any of slave db.
- when face a surge of queries, load balancer can distribute load across multiple servers
- suitable for cases when more read heavy and less write heavy

Disadvanatge:

