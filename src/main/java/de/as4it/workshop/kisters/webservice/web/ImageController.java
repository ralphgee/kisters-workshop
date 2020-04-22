package de.as4it.workshop.kisters.webservice.web;

import de.as4it.workshop.kisters.webservice.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Controller
public class ImageController {

    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/greeting",method = RequestMethod.GET)
    public String showImages(Model model) {
        model.addAttribute("name","KISTERS");
        return "greeting";
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
