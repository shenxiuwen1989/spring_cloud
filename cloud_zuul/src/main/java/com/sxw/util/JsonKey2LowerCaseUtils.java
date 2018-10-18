/*
 * @(#)JSONtoLowerTools.java 1.0 2018年7月28日
 * @Copyright:  Copyright © 2007-2018 ky-express.com.All Rights Reserved.
 * @Description: 
 * 
 * @Modification History:
 * @Date:        2018年7月28日
 * @Author:      lucius.lv
 * @Version:     1.0.0.0
 * @Description: (Initialize)
 * @Reviewer:    
 * @Review Date: 
 */
package com.sxw.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;

/**
 * 将json所有的key转换为小写
 */
public class JsonKey2LowerCaseUtils {

    public static JSONObject transObject(JSONObject o1){
        JSONObject o2=new JSONObject();
         Iterator<String> it = o1.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                Object object = o1.get(key);
                if(object == null) {
                    continue;
                }
                Class<?> clazz = object.getClass();
                if(clazz != null) {
                    String shortClassName = ClassUtils.getShortClassName(clazz);
                    if(StringUtils.equalsIgnoreCase("JSONObject", shortClassName)){
                        o2.fluentPut(StringUtils.lowerCase(key), transObject((JSONObject)object));
                    }else if(StringUtils.equalsIgnoreCase("JSONArray", shortClassName)){
                        o2.fluentPut(StringUtils.lowerCase(key), transArray(o1.getJSONArray(key)));
                    }else {
                        o2.fluentPut(StringUtils.lowerCase(key), object);
                    }
                }

                
            }
            return o2;
    }

    public static JSONArray transArray(JSONArray o1){
        JSONArray o2 = new JSONArray();
        for (int i = 0; i < o1.size(); i++) {
            Object obj=o1.get(i);
            if(obj == null) {
                continue;
            }
            Class<?> clazz = obj.getClass();
            if(clazz != null) {
                String shortClassName = ClassUtils.getShortClassName(clazz);
                if(StringUtils.equalsIgnoreCase("JSONObject", shortClassName)){
                    o2.add(transObject((JSONObject)obj));
                }else if(StringUtils.equalsIgnoreCase("JSONArray", shortClassName)){
                    o2.add(transArray((JSONArray)obj));
                }else {
                    o2.add(obj);
                }
            }
        }
        return o2;
    }

}

