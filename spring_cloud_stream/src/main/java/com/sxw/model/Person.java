package com.sxw.model;


import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@ToString
public class Person {

    private String name;
    private int age;
}
