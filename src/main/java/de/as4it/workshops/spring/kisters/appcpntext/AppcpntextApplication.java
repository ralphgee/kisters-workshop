package de.as4it.workshops.spring.kisters.appcpntext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@ImportResource({"classpath*:applicationContext.xml"})
public class AppcpntextApplication {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext appContext = SpringApplication.run(AppcpntextApplication.class, args);
    }

}
