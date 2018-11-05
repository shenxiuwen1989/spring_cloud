package com.sxw.controller;


import com.sxw.entry.ErpResQueryLogisticsTakeTime;
import com.sxw.entry.ReturnObject;
import com.sxw.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private BaseService baseService;

    @RequestMapping(value = "/prc",method = RequestMethod.POST)
    public String prc(){
        Map<String, Object> requestParameter = new HashMap<>();
        requestParameter.put("waybillNumber", "123456");
        ReturnObject<List<ErpResQueryLogisticsTakeTime>> returnObject = baseService.queryLogisticsTakeTime(requestParameter);
        return "成功";
    }
}
