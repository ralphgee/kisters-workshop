package de.as4it.workshop.kisters.webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.as4it.workshop.kisters.webservice.domain.Image;
import de.as4it.workshop.kisters.webservice.rest.ImageRestErrorJson;
import de.as4it.workshop.kisters.webservice.rest.ImageServiceRestClient;
import de.as4it.workshop.kisters.webservice.rest.RestTemplateResponseErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@RestClientTest(ImageServiceRestClient.class)
public class RestClientTests {

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        public RestTemplateResponseErrorHandler restTemplateResponseErrorHandler(ObjectMapper mapper) {
            return RestTemplateResponseErrorHandler.builder().mapper(mapper).build();
        }
    }

    @Autowired
    private ImageServiceRestClient client;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private RestTemplateResponseErrorHandler restTemplateResponseErrorHandler;

    @Autowired
    ObjectMapper mapper;

    private RestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        template = restTemplateBuilder.errorHandler(restTemplateResponseErrorHandler).build();
        server = MockRestServiceServer.bindTo(template).build();
    }

    @Test
    public void testInfoResourceWithSuccess() {
        this.server.reset();
        this.server.expect(requestTo("/images/info"))
                .andRespond(withSuccess("This is the Image REST-ful API", MediaType.TEXT_PLAIN));
        Assertions.assertThat(this.client.getInfo()).isEqualTo("This is the Image REST-ful API");
    }

    @Test
    public void test500ErrorHandling() throws JsonProcessingException {
        this.server.reset();
        ImageRestErrorJson error = new ImageRestErrorJson("test500ErrorHandling");
        this.server.expect(ExpectedCount.manyTimes(), requestTo("/500"))
                .andRespond(withServerError().body(mapper.writeValueAsString(error)));

        Assertions.assertThatCode(() -> template.getForObject("/500", Image.class))
                .doesNotThrowAnyException();
    }

    @Test
    public void test403ErrorHandling() {
        this.server.reset();
        ;
        this.server.expect(ExpectedCount.manyTimes(), requestTo("/403")).andRespond(withStatus(HttpStatus.FORBIDDEN));
        Assertions.assertThatThrownBy(() -> template.getForEntity("/403", Image.class)).isInstanceOf(HttpClientErrorException.Forbidden.class);
    }
}
