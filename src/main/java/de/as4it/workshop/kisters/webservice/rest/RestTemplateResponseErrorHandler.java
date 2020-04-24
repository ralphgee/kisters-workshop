package de.as4it.workshop.kisters.webservice.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.With;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Slf4j
@Data
@Builder
public class RestTemplateResponseErrorHandler extends DefaultResponseErrorHandler {

    ObjectMapper mapper;

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return clientHttpResponse.getStatusCode().isError();
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        if (clientHttpResponse.getStatusCode().is5xxServerError()) {
            ImageRestErrorJson error = mapper.readValue(clientHttpResponse.getBody(),ImageRestErrorJson.class);
            log.error("RestTemplate Server Error: {}", error.getMessage());
        } else {
            super.handleError(clientHttpResponse);
        }
    }
}

