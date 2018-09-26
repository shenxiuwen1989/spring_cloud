
使用到了spring cloud组件
    1.eureka做客户端发现服务使用
    2.ribbon做负载均衡使用
    3.hystrix做服务容错/降级
        3.1 pom文件引入hystrix的依赖包
        3.2 @EnableCircuitBreaker   //开启断路功能 
使用了mockMvc做单元测试，实现测试完整的Spring MVC流程，即从URL请求到控制器处理，再到视图渲染都可以测试。        
        
        
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
    
    