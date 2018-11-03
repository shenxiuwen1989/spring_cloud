/*
 * @(#)FeignClientConfiguration.java 1.0 2018年8月2日
 * @Copyright:  Copyright © 2007-2018 ky-express.com.All Rights Reserved.
 * @Description: 
 * 
 * @Modification History:
 * @Date:        2018年8月2日
 * @Author:      lucius.lv
 * @Version:     1.0.0.0
 * @Description: (Initialize)
 * @Reviewer:    
 * @Review Date: 
 */
package com.sxw.config;


import com.sxw.log.FeignSlf4jLogger;
import feign.Contract;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

@Configuration
@Slf4j
public class FeignClientConfiguration {
    
    private final Timer connectionManagerTimer = new Timer("FeignApacheHttpClientConfiguration.connectionManagerTimer", true);
    
    @Autowired
    private FeignHttpClientConfiguration config;

    
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    
    @Bean
    public Logger feignLogger() {
        return FeignSlf4jLogger.LOG;
    }
    
    @Bean
    public Contract feignConfiguration(){
        return new Contract.Default();
    }
    
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                    Map<String, Collection<String>> headers = template.headers();
                    
                    //获取请求体json字符串
                    String paramJson = new String(template.body(),StandardCharsets.UTF_8);

                    //设置消息头
                    Map<String, String> keyHttpHeader = new HashMap<>();
                    keyHttpHeader.put("timestamp",String.valueOf(System.currentTimeMillis()));
                    keyHttpHeader.put("Accept", "application/json");
                    keyHttpHeader.put("Content-Type", "application/json");
                    keyHttpHeader.forEach((k,v) -> template.header(k, v));
            }
        };
    }
    
    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager(SSLConnectionSocketFactory socketFactory) {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory> create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", socketFactory).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        // 同路由的并发数
        cm.setDefaultMaxPerRoute(config.getMaxPerRoute());
        // 总连接数
        cm.setMaxTotal(config.getMaxTotal());
        //可用空闲连接过期时间，重用空闲连接时会先检查是否空闲时间超过这个时间，如果超过，释放socket重新建立
        cm.setValidateAfterInactivity(config.getValidateAfterInactivity());
        
        //定时清理过期的连接
        this.connectionManagerTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                cm.closeExpiredConnections();
            }
        }, 30000, config.getConnectionTimerRepeat());
        
        return cm;
    }
    
    @Bean
    public SSLConnectionSocketFactory socketFactory() {
        try {
            TrustManager manager = new X509TrustManager() {
                @Override
                public void checkClientTrusted( X509Certificate[] x509Certificates, String s) throws CertificateException {
                    
                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            // SSLv3
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new TrustManager[] { manager }, null);
            return new SSLConnectionSocketFactory(context,NoopHostnameVerifier.INSTANCE);
        } catch (Exception e) {
            log.error("创建SSL连接失败",e);
        }
        return null;
    }
    
    
    @Bean
    public HttpClient httpClient(RequestConfig requestConfig,PoolingHttpClientConnectionManager poolingHttpClientConnectionManager){
        log.info("init feign httpclient configuration begin... ...");
        CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(requestConfig)
                .setConnectionManager(poolingHttpClientConnectionManager)
                //保持长连接配置，需要在头添加Keep-Alive
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setRetryHandler(new DefaultHttpRequestRetryHandler(config.getRetryTimes(), config.isRequestSentRetryEnabled()))
                .build();
        log.info("init feign httpclient configuration finished... ...");
        return client;
    }
    
    @Bean
    public RequestConfig requestConfig() {
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(config.getSocketTimeout())
                .setConnectTimeout(config.getConnectTimeout())
                .setConnectionRequestTimeout(config.getConnectTimeout())
                .setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .setRedirectsEnabled(false).setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                .build();
        return requestConfig;
    }
    

}

