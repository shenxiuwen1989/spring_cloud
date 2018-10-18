package com.sxw.entry;

import com.sxw.validator.StringSplitLength;

public class Student {
    //姓名
    private String name;
    //年龄
    private String age;
    //性别
    private String gender;
    //爱好,多个爱好用“,”隔开
    @StringSplitLength(separator=",",max=3)
    private String hobby;
}
