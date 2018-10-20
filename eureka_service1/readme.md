
eureka_service做注册中心

为什么不用zk服务注册中心：
1.ZooKeeper无法很好的处理网络分区问题，当网络分区中的客户端节点无法到达Quorum时，会与ZooKeeper失去联系，
    从而也就无法使用其服务发现机制。
2.服务发现系统应该是一个AP系统，设计上针对可用性；而ZooKeeper是一个CP系统。
3.ZooKeeper的设置和维护非常困难，实际操作的时候也容易出错，
    比如在客户端重建Watcher，处理Session和异常的时候。
当然，Peter Kelley提出的这几个问题并不是不能克服的，并不能说明基于ZooKeeper就不能做好一个服务发现系统，
    但是我们可能有更简洁的方案来实现。


搭建项目时踩过的坑：
（1） org.apache.http.conn.ConnectTimeoutException: Connect to localhost:8761 timed out
    修改eureka.client.register-with-eureka=false；
（2）：com.netflix.discovery.shared.transport.TransportException: Cannot execute request on any known server
     修改eureka.client.fetchRegistry=false
     
 (3)不能这样写：
    spring.application.name=eureka_service_01
    eureka.instance.hostname=01_server_eureka
    要改成 spring.application.name=eureka-service-01
      eureka.instance.hostname=01.server.eureka
    因为在eureka中java.net.URI不能区分下划线(反正不能用下划线即可)，所以切记，当使用host作为defaultZone配置时，不要以下划线来命名
    
 (4)输入http://127.0.0.1:1111/可查看注册服务
 