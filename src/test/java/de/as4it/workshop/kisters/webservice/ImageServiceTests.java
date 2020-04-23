package de.as4it.workshop.kisters.webservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ImageServiceTests {

    @Autowired
    ImageService imageService;

    @Test
    public void testContractForFindById(){
        assertThatThrownBy(() -> { imageService.findById(0); }).isInstanceOf(ConstraintViolationException.class);
    }
}
