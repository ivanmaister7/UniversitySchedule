package com.schedule.proj;

//import com.shedule.starter.mybeansspringbootstarter.service.MyBean;

import org.apache.logging.log4j.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan("com.schedule.proj.repository")
public class ProjApplication {
    private static final Logger logger = LogManager.getLogger();
    final static Marker DB_ERROR = MarkerManager.getMarker("DATABASE_ERROR");

    public static void main(String[] args) {

        SpringApplication.run(ProjApplication.class, args);

        ThreadContext.put("username","admin");
        ThreadContext.put("ID", "37614");
        logger.info("Login successful");
        ThreadContext.clearMap();
        ThreadContext.put("username","dev1");
        ThreadContext.put("ID", "33894");
        logger.warn("Something to warn");
        ThreadContext.clearMap();
        ThreadContext.put("username","admin");
        ThreadContext.put("ID", "37614");
        logger.error(DB_ERROR,"Table not exist.");
        ThreadContext.clearMap();
    }

}
