package com.jiang.school_guide;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.jiang.school_guide.dao")
public class SchoolGuideApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolGuideApplication.class, args);
    }

}
