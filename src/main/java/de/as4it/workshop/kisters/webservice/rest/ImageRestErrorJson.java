package de.as4it.workshop.kisters.webservice.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect
@Data
@NoArgsConstructor
public class ImageRestErrorJson {
    String message;

    public ImageRestErrorJson(String message) {
        this.message = message;
    }
}

