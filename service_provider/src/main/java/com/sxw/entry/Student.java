package com.sxw.entry;


import com.sxw.validator.StringSplitLength;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class Student {
    //姓名
    @NotNull(message = "不能为空")
    private String name;
    //年龄
    @Max(value = 100)
    private String age;
    //性别
    private String gender;
    //爱好,多个爱好用“,”隔开
    @NotNull(message = "不能为空")
    @StringSplitLength(separator=",",max=3,message="最多只能3个")
    private String hobby;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
