package com.kaseihaku.bpm.app.one;


import com.kaseihaku.boot.starter.redis.autoconfigure.RedisPropertySourceAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(exclude = {
    /* 框架默认 redis 是 cluster 模式，如果想要 standalone 模式需要把该行放出来
     * 并且把 application.yml 中 spring.data.redis 部分注释放出来
     * */
    RedisPropertySourceAutoConfiguration.class,
    MybatisAutoConfiguration.class
})
@MapperScan(basePackages = {"com.kaseihaku.bpm.app.one.repo.dao.mapper"})
public class BpmAppOneBoot {

    public static void main(String[] args) {
        SpringApplication.run(BpmAppOneBoot.class, args);
    }
}
