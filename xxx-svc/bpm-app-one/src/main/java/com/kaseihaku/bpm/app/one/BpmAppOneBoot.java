package com.kaseihaku.bpm.app.one;


import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(exclude = {MybatisAutoConfiguration.class})
@MapperScan(basePackages = {"com.kaseihaku.bpm.app.one.repo.dao.mapper"})
public class BpmAppOneBoot {

    public static void main(String[] args) {
        SpringApplication.run(BpmAppOneBoot.class, args);
    }
}
