package com.chryl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({
        "com.chryl.mapper"
//        ,
//        "com.chryl.quartz.dao"//已在mybatis-plus配置文件里写了
})
public class VueTemlpateApplication {

    public static void main(String[] args) {
        SpringApplication.run(VueTemlpateApplication.class, args);
    }

}
