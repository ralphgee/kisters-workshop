package de.as4it.workshop.kisters.webservice;

import de.as4it.workshop.kisters.webservice.domain.Image;
import de.as4it.workshop.kisters.webservice.domain.Image_;
import de.as4it.workshop.kisters.webservice.repository.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.Min;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
@Slf4j
public class ImageService {

    private ImageRepository repository;
    private ImageAsyncService imageAsyncService;

    @Autowired
    public ImageService(ImageRepository repo,ImageAsyncService async){
        this.repository = repo; this.imageAsyncService = async;
    }

    public Image findById(@Min(1) int nr) {
        return repository.findById((long) nr).orElse(null);
    }

    public void saveAll(List<Image> images) {
        repository.saveAll(images);
    }

    public List<Image> findAllHostedByLorempixel(){
        return repository.findAll(imageIsPublishedBeforeToday());
    }

    public static Specification<Image> imageIsPublishedBeforeToday() {
        return new Specification<Image>() {
            @Override
            public Predicate toPredicate(Root<Image> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return  criteriaBuilder.lessThan(root.get(Image_.publishedAt), LocalDate.now());
            }
        };
    }

    public List<Image> findAll() {

//        Image i = Image.builder().id(1L).lastModified(LocalDate.now()).build();
//        i.withId(1L).withName("Name");
        //Image.of(1L,"name",null,null,null);
        //Image i2 = new Image()


        List<Image> returnList = new ArrayList<>();
        repository.findAll().forEach(e -> returnList.add(e));
        return returnList;

    }

    public void save(Image image) {
        repository.save(image);
        imageAsyncService.delegateLongRunningTask();
    }

    @Scheduled(fixedDelay = 30000,initialDelay = 50000)
    public void maintainImages(){
        log.info("Executing Maintain Images");
    }
}
