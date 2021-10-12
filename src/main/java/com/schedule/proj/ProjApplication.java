package com.schedule.proj;

import com.shedule.starter.mybeansspringbootstarter.service.MyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan("com.schedule.proj.repository")
public class ProjApplication implements CommandLineRunner{

    @Autowired
    MyBean myBean;

    public static void main(String[] args) {
        SpringApplication.run(ProjApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception  {
        myBean.showBean();
    }

}
