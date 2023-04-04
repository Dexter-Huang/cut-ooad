package com.exchange;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement
@EnableSwagger2
@MapperScan("com.exchange.mapper")
@EnableScheduling
public class ExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeApplication.class, args);
    }

    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskExecutor=new ThreadPoolTaskScheduler();
        taskExecutor.setPoolSize(15);
        return taskExecutor;
    }
}
