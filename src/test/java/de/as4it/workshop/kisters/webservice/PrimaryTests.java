package de.as4it.workshop.kisters.webservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.as4it.workshop.kisters.webservice.domain.Image;
import de.as4it.workshop.kisters.webservice.rest.RestTemplateResponseErrorHandler;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.management.MXBean;

@SpringBootTest
public class PrimaryTests {

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        @Primary
        Image primaryImage() {
            Image pImage = new Image();
            pImage.setName("PrimaryImage");
            return pImage;
        }

        @Bean
        @Qualifier("secondary")
        Image secondaryImage() {
            Image pImage = new Image();
            pImage.setName("SecondaryImage");
            return pImage;
        }
    }

    @Autowired
    Image image;

    @Autowired
    Image primaryImage;

    @Autowired
    @Qualifier("secondary")
    Image secondaryImage;

    @Test
    void testImages() {
        Assertions.assertThat(image.getName()).isEqualTo("PrimaryImage");
        Assertions.assertThat(primaryImage.getName()).isEqualTo("PrimaryImage");
        Assertions.assertThat(secondaryImage.getName()).isEqualTo("SecondaryImage");
    }
}
