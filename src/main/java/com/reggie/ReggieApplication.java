package com.reggie;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.writer.ObjectWriters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReggieApplication {

    public static void main(String[] args) {
        JSON.register(Long.class, ObjectWriters.ofToString(Object::toString));
        SpringApplication.run(ReggieApplication.class, args);
    }

}
