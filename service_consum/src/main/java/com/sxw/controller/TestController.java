package com.sxw.controller;

import com.sxw.entry.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api-b")
public class TestController {


    @RequestMapping(value = "/callHello1",method = RequestMethod.GET)
    public String sayHellow(){
        return  "hello";
    }

    @RequestMapping(value = "/updateStudent",method = RequestMethod.GET)
    public String updateStudent(@Valid Student student){

        return  "success";
    }
}
