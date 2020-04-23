package de.as4it.workshop.kisters.webservice.web;

import de.as4it.workshop.kisters.webservice.ImageService;
import de.as4it.workshop.kisters.webservice.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Controller
public class ImageController {

    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/greeting",method = RequestMethod.GET)
    public String showImages(Model model) {
        model.addAttribute("name","KISTERS");
        return "greeting";
    }

    @RequestMapping(value="/gallery",method = RequestMethod.GET)
    public String gallery(Model model){

        List<Image> images = imageService.findAll();
        model.addAttribute("images",images);
        return "gallery";
    }

    @RequestMapping(value="/gallery/{id}",method = RequestMethod.GET)
    public String gallerySingleImage(@PathVariable("id") int id,  Model model){

        Image image = imageService.findById(id);
        model.addAttribute("image",image);
        return "gallerySingle";
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<String> handleUpload(@RequestParam(value = "file") MultipartFile uploadedFile) {

        try {
            //write file to disk or somewhre else or process it ....
        } catch (Exception e) {
            throw new RuntimeException("Unable to upload file", e);
        }
        return ResponseEntity.ok("");
    }
}
