package com.sxw.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.sxw.wrapper.TransRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求过滤
 */
public class AccessFilter extends ZuulFilter {

    /**
     * 过滤器类型：它决定过滤器在请求的哪个生命周期中执行
     * pre : 代表会在请求被路由之前执行
     * routing ： 路由请求时被调用
     * post ： 代表会在请求被路由之后执行（在routing和error过滤器之后执行）
     * error: 处理请求时或者以上任何一个周期发生错误时调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断该过滤器是否需要被执行。这里我们直接返回true，因此该过滤器对所有请求都会生效。
     * 实际运用中我们可以利用该函数来指定过滤器的有效范围
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }


    /**
     * 过滤器具体逻辑
     * @return
     */
    @Override
    public Object run() {

        RequestContext ctx = com.netflix.zuul.context.RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        response.setContentType("text/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");

        //1.鉴权



        //备份request
        ctx.setRequest(new TransRequestWrapper(request));


        return null;
    }
}
