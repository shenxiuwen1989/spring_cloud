
1.实现功能
    1.1 Validator和ConstraintValidator实现入参自定义注解校验
       JDK中自带Validator自带注解有：
       
            
    1.2


踩过的坑：
1、com.sun.jersey.api.client.ClientHandlerException: java.net.ConnectException: Connection refused: connect
或者com.netflix.discovery.shared.transport.TransportException: Cannot execute request on any known server
原因如下：
在默认设置下，Eureka服务注册中心也会将自己作为客户端来尝试注册它自己，所以我们需要禁用它的客户端注册行为。
禁止方式如下：在application.properties配置文件中增加以下内容
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
