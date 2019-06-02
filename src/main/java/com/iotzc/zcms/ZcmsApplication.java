package com.iotzc.zcms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.iotzc.zcms.dao")
public class ZcmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZcmsApplication.class, args);
    }

}
