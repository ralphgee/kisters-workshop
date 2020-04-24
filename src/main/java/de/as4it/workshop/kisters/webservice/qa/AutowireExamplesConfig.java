package de.as4it.workshop.kisters.webservice.qa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutowireExamplesConfig {
    @Bean
    String s1(){return "ByField";}
    @Bean
    String s2(){return "BySetter";}
    @Bean
    String s3(){return "byConstructor";}
}
