package com.reggie;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.writer.ObjectWriters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class ReggieApplication {

    public static void main(String[] args) {
        JSON.register(Long.class, ObjectWriters.ofToString(Object::toString));
        SpringApplication.run(ReggieApplication.class, args);
    }

}
