package com.gupao.springbootdemo.initialization;

import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SnowFlakeInitStart implements CommandLineRunner, ApplicationContextAware {

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(" ##@@## 调用GPRC服务初始化 Work ID");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
