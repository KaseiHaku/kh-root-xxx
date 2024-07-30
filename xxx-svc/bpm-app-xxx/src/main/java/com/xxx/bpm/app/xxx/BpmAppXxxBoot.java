package com.xxx.bpm.app.xxx;


// import org.mybatis.spring.annotation.MapperScan;
// import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication(exclude = {MybatisAutoConfiguration.class})
@SpringBootApplication
// @MapperScan(basePackages = {"com.xxx.bpm.app.xxx.repo.dao.mapper"})
public class BpmAppXxxBoot {

    public static void main(String[] args) {
        SpringApplication.run(BpmAppXxxBoot.class, args);
    }
}
