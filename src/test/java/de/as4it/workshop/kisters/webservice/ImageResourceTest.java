package de.as4it.workshop.kisters.webservice;

import org.junit.jupiter.api.Test;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by andy on 27.01.17.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImageResourceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int port;

    private String host = "localhost";

    @Test
    public void testInfoResource() throws Exception {
        assertThat(restTemplate).isNotNull();
        assertThat(port).isGreaterThan(0);

        RequestEntity request = RequestEntity.get(new URI(MessageFormatter.arrayFormat("http://{}:{}/images/info", new Object[]{host, (long) port}).getMessage()))
                                .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                                .build();
        ResponseEntity<String> result = restTemplate.exchange(request, String.class);
        assertThat(result).isNotNull();
        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(result.getBody()).isNotEmpty();
        assertThat(result.getBody()).contains("This is the Image REST-ful API");
    }
}
