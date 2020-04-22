package de.as4it.workshop.kisters.webservice.repository;

import de.as4it.workshop.kisters.webservice.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("init")
public class ImageDataInitilizer {

    private final ImageRepository images;

    @Autowired
    public ImageDataInitilizer(ImageRepository repo){
        this.images =  repo;
    }

    @PostConstruct
    public void setup() throws Exception {
        List<Image> data = new ArrayList<>();
        data.add(new Image("Image1", new URL("https://picsum.photos/400/200"), LocalDate.now(),LocalDate.now()));
        data.add(new Image("Image2", new URL("https://picsum.photos/1280/1024"),LocalDate.now(),LocalDate.now()));
        data.add(new Image("Image3", new URL("https://picsum.photos/500/500"),LocalDate.now(),LocalDate.now()));
        data.add(new Image("Image4", new URL("https://picsum.photos/640/480"),LocalDate.now(),LocalDate.now()));
        data.add(new Image("Image5", new URL("https://picsum.photos/800/600"),LocalDate.now(),LocalDate.now()));
        images.saveAll(data);
    }
}
