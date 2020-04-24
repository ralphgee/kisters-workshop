package de.as4it.workshop.kisters.webservice.qa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = { "spring.main.allow-bean-definition-overriding=true" })
public class AutowireWithConstructorAsSpringBootTests {

    @TestConfiguration
    static class AutowireWithConstructorAsSpringBootTestsConfig {
        @Bean
        String s1(){return "TestWithSpring";}
    }

    @Autowired
    private AutowireExamples examples;

    @Test
    public void testAutowireExamplesWithTestString(){
        Assertions.assertThat(examples.s1).isEqualTo("TestWithSpring");
    }
}
