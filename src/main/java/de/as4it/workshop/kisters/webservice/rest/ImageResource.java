package de.as4it.workshop.kisters.webservice.rest;

import de.as4it.workshop.kisters.webservice.ImageService;
import de.as4it.workshop.kisters.webservice.domain.Image;
import de.as4it.workshop.kisters.webservice.rest.exception.ImageNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by andy on 14.01.16.
 */

@RestController
@RequestMapping("/images")
public class ImageResource {


    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info() {
        return "This is the Image REST-ful API";
    }

    @Operation(summary = "Gets all existing Images",
            description = "Provides a images as List")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Image> getAllImages() {
        return imageService.findAll();
    }

    @Operation(summary = "Gets all existing Images - XML",
            description = "Provides a images as List as XML")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public List<Image> getAllImagesAsXML() {
        return imageService.findAll();
    }



    @Operation(summary = "Gets an existing Images as JPEG",
            description = "Gets an existing Images as JPEG")
    @RequestMapping(value = "/{nr}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<InputStreamResource> showImage(@PathVariable("nr") int nr) throws ImageNotFoundException, IOException {
        Optional<Image> image = Optional.ofNullable(imageService.findById(nr));
        return ResponseEntity.ok().body(new InputStreamResource(image.orElseThrow(() -> new ImageNotFoundException()).getLocation().openStream()));
    }

    @Operation(summary = "Gets an existing Image",
            description = "Gets an existing Image")
    @RequestMapping(value = "/{nr}", method = RequestMethod.GET)
    public Image showImageAsJSON(@PathVariable("nr") int nr) {
        Image image = imageService.findById(nr);
        return image;
    }

    @Operation(summary = "Stores a new Image",
            description = "Stores a new Image")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createImage(@Valid @RequestBody Image image) {
        imageService.save(image);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
