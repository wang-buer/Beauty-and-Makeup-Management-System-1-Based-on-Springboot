package com.mh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages ="com.mh.*" )
@MapperScan("com.mh.*.mapper")
public class APP{
    public static void main(String[] args) {
        SpringApplication.run(APP.class,args);
    }
}
