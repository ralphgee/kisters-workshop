package de.as4it.workshop.kisters.webservice.repository;

import de.as4it.workshop.kisters.webservice.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URL;
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
        data.add(new Image("Image", new URL("http://lorempixel.com/400/200/sports/"),null,null));
        data.add(new Image("Image", new URL("http://lorempixel.com/1280/1024/sports/"),null,null));
        data.add(new Image("Image", new URL("http://lorempixel.com/500/500/sports/"),null,null));
        data.add(new Image("Image", new URL("http://lorempixel.com/640/480/sports/"),null,null));
        data.add(new Image("Image", new URL("http://lorempixel.com/800/600/sports/"),null,null));
        images.saveAll(data);
    }
}
