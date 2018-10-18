
实现的功能点
    1.eureka做客户端发现服务使用
    2.ribbon做负载均衡使用
    3.hystrix做服务容错/降级
        3.1 pom文件引入hystrix的依赖包
        3.2 @EnableCircuitBreaker   //开启断路功能 
使用了mockMvc做单元测试，实现测试完整的Spring MVC流程，即从URL请求到控制器处理，再到视图渲染都可以测试。        
    4.config 和 eureka 组合可以做高可用集群获取配置中心
    5.bootstrap与application的加载顺序和区别
    6.
踩过的坑
    1.Fetching config from server at: http://localhost:8888
       通过启动日志可以发现：服务还是去 默认端口8888取配置中心的文件；这是怎么回事呢。其实就是一个配置文件优先级的问题；SpringCloud里面有个“启动上下文”,主要是用于加载远端的配置,也就是加载ConfigServer里面的配置,默认加载顺序为:加载bootstrap.*里面的配置 --> 链接configserver,加载远程配置 --> 加载application.*里面的配置; 总结:这里需要借助于“启动上下文”来处理加载远程配置；
       所以只要将配置文件改为bootstrap.properties或bootstrap.yml文件即可。
    2.当eureka+hystrix+ribbon没出现问题，但是当再集成config后就不能进入controller里面的方法了
        
备注：
    1.hystrix默认超时时间为2000毫秒，超过2000毫秒就会调用回调函数
    2.hystrix多线程环境中的故障​​，延迟，超时和重复调用
    3.MvcResult
        即执行完控制器后得到的整个结果，并不仅仅是返回值，其包含了测试时需要的所有信息。
        MockHttpServletRequest getRequest()：得到执行的请求；
        MockHttpServletResponse getResponse()：得到执行后的响应；
        Object getHandler()：得到执行的处理器，一般就是控制器；
        HandlerInterceptor[] getInterceptors()：得到对处理器进行拦截的拦截器；
        ModelAndView getModelAndView()：得到执行后的ModelAndView；
        Exception getResolvedException()：得到HandlerExceptionResolver解析后的异常；
        FlashMap getFlashMap()：得到FlashMap；
        Object getAsyncResult()/Object getAsyncResult(long timeout)：得到异步执行的结果；
    4.如何实现hystrix默认集群监控/指定的集群监控/单体应用的监控
    5.利用spring cloud config从config配置中心获取配置    
    6.https://springcloud.cc/spring-cloud-netflix-zhcn.html 
        https://segmentfault.com/a/1190000011698823
    7.validator和ConstraintValidator结合自定义校验
        7.1必须使用@Valid标注我们需要校验的参数
        7.2我们在定义自己的限制类型的注解时有三个属性是必须定义的，如上面代码所示的message、groups和payload属性
    