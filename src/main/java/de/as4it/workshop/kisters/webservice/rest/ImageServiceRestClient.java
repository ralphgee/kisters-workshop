package de.as4it.workshop.kisters.webservice.rest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ImageServiceRestClient {
    private final RestTemplate restTemplate;

    public ImageServiceRestClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public String getInfo() {
        return restTemplate.getForObject("/images/info",String.class);
    }

}
