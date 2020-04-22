package de.as4it.workshop.kisters.webservice.rest;

import de.as4it.workshop.kisters.webservice.ImageService;
import de.as4it.workshop.kisters.webservice.domain.Image;
import de.as4it.workshop.kisters.webservice.repository.ImageRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andy on 14.01.16.
 */

@RestController
@RequestMapping("/images")
public class ImageResource {


     @Autowired
    ImageService imageService;

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public String info() {
        return "This is the Image REST-ful API";
    }


    @ApiOperation(value = "Gets all existing Images",
            notes = "Provides a images as List",
            response = Image.class,
            responseContainer = "List")
    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<Image> getAllImages() {
        return imageService.findAll();
    }

    @ApiOperation(value = "Gets all existing Images - XML" ,
            notes = "Provides a images as List as XML",
            response = Image.class,
            responseContainer = "List")
    @RequestMapping(value = "",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_XML_VALUE)
    public List<Image> getAllImagesAsXML() {
        return imageService.findAll();
    }

    @RequestMapping(value = "/{nr}", method = RequestMethod.GET, params = "format=image")
    @ResponseBody
    public ResponseEntity<InputStreamResource> showImage(@PathVariable("nr") int nr) {
        Image image = imageService.findById(nr);
        try {
            return
                    ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(image.getLocation().openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @RequestMapping(value = "/{nr}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Image showImageAsJSON(@PathVariable("nr") int nr) {
        Image image = imageService.findById(nr);
        return image;
    }

}
