System design

#### Scalability 

Q: What if website faces a surge of requests

#### Ways of scaling

- Vertical scaling: 
  - upgrade the ability of physical machine (RAM, CPU, storage)
  - drawback: due to real world constraints (financial or technology constraints), there is a limit that the machine you can upgrade to
- Horizontal scaling: add more cheaper servers in to the resource pool

#### Load balancer

![Outbound traffic with Standard Load Balancer Microsoft Azure | Marius Sandbu](https://msandbu.org/wp-content/uploads/2019/08/AWS-ELB-diagram.jpg)

Helps to distribute requests from users.

Step 1: which IP address return to user? 

- Without load balancer: return IP address of specific server (e.g. 1, 2, 3)
- With load balancer: return IP address of load balancer. Load balancer can route data to servers. Servers can have private IP address, which cannot be connected from random hosts.

Step 2: how load balancer determine which server to process data?

- round robin: client request is forwarded to each server in turn
- randomness



