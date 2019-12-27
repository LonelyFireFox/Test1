package pers.zyj.jd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//这个配置等同于：@Configuration ，@EnableAutoConfiguration 和 @ComponentScan 三个配置。
//使用定时任务，需要先开启定时任务，需要添加注解
@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
