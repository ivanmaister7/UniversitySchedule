package com.schedule.proj;

//import com.shedule.starter.mybeansspringbootstarter.service.MyBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@SpringBootApplication
public class ProjApplication {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        SpringApplication.run(ProjApplication.class, args);

        logger.info("Log4j2ExampleApp started.");
        logger.warn("Something to warn");
        logger.error("Something failed.");
            /*try {
                Files.readAllBytes(Paths.get("/file/does/not/exist"));
            } catch (IOException ioex) {
                logger.error("Error message", ioex);
            }*/
    }

}
