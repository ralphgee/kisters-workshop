package de.as4it.workshop.kisters.webservice.rest;

import de.as4it.workshop.kisters.webservice.rest.exception.ImageNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice(basePackageClasses = ImageResource.class) //@ControllerAdvice + @ResponseBody
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(ImageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> imageNotFound(RuntimeException ex) {
        log.error("RestController Exception", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(HttpServletRequest request, Throwable ex) {
        log.error("RestController Exception", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ImageRestErrorJson(ex.getMessage()));
    }
}

