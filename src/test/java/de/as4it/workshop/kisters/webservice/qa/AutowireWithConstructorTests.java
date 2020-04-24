package de.as4it.workshop.kisters.webservice.qa;

import de.as4it.workshop.kisters.webservice.qa.AutowireExamples;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowire;

public class AutowireWithConstructorTests {

    private AutowireExamples examples;

    @BeforeEach
    private void setUp(){
        examples =  new AutowireExamples("TestWithoutSpring");
    }

    @Test
    public void testAutowireExamplesWithTestString(){
        Assertions.assertThat(examples.StringByConstructor).isEqualTo("TestWithoutSpring");
    }
}
