package com.sxw;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Before;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.*;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class MockMvcTest {

    @Autowired
    private WebApplicationContext wac;//注入web环境的ApplicationContext容器；
    private MockMvc mockMvc;
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();   //构造MockMvc
    }

    public void sendRequest(String docs,String url){

        //声明是发送“application/json”格式的数据
        MockHttpServletRequestBuilder requestBuilder = post(url).contentType(MediaType.APPLICATION_JSON).content(docs);

        try {
            ResultActions resultActions =   mockMvc.perform(requestBuilder);//执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
            resultActions.andDo(print());//添加一个结果处理器，比如此处控制台输入输出整个响应结果信息，可以在调试的时候使用
            resultActions.andExpect(status().isOk());//添加执行完成后的断言
          //  resultActions.andDo(document(docs + url));
            MvcResult result =resultActions.andReturn();//执行完成后返回相应的结果



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
/**
 * (1)@WebAppConfiguration：测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的；
 *
 */
