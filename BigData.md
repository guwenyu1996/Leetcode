#### 数据仓库分层

![1612233327640](C:/Users/wenyu/AppData/Roaming/Typora/typora-user-images/1612233327640.png)

**数据运营层（ODS）**

- ODS：Operation Data Store 数据准备区，也称为贴源层。数据仓库源头系统的数据表通常会原封不动的存储一份，这称为ODS层，是后续数据仓库加工数据的来源。
- ODS层数据的来源方式：
  - 业务库
    - 经常会使用sqoop来抽取，例如每天定时抽取一次。
    - 实时方面，可以考虑用canal监听mysql的binlog，实时接入即可。
  - 埋点日志
    - 日志一般以文件的形式保存，可以选择用flume定时同步
    - 可以用spark streaming或者Flink来实时接入
    - kafka也OK
  - 消息队列：即来自ActiveMQ、Kafka的数据等。

**数据仓库层（DW）**

DW数据分层，由下到上为DWD，DWB，DWS。

- DWD：data warehouse details 细节数据层，是业务层与数据仓库的隔离层。主要对ODS数据层做一些

  数据清洗和规范化

  的操作。

  - 数据清洗：去除空值、脏数据、超过极限范围的

- DWB：data warehouse base 数据基础层，存储的是客观数据，一般用作中间层，可以认为是大量指标的数据层。

- DWS：data warehouse service 数据服务层，基于DWB上的基础数据，整合汇总成分析某一个主题域的服务数据层，一般是宽表。用于提供后续的业务查询，OLAP分析，数据分发等。

  - 用户行为，轻度聚合
  - 主要对ODS/DWD层数据做一些轻度的汇总。

**数据服务层/应用层（ADS）**

- ADS：applicationData Service应用数据服务，该层主要是提供数据产品和数据分析使用的数据，一般会存储在ES、mysql等系统中供线上系统使用。
  - 我们通过说的报表数据，或者说那种大宽表，一般就放在这里