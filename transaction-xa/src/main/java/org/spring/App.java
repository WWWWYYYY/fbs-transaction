package org.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication//默认扫描和启动类同目录下的所有包和类，否则使用@ComponentScan指定要扫描的包
@EnableTransactionManagement //启动事务
public class App {


    public static void main(String[] args) {
        //启动类
        //定制化
        SpringApplication.run(App.class, args);
    }
}
