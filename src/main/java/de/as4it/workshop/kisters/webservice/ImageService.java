package de.as4it.workshop.kisters.webservice;

import de.as4it.workshop.kisters.webservice.domain.Image;
import de.as4it.workshop.kisters.webservice.domain.Image_;
import de.as4it.workshop.kisters.webservice.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    private ImageRepository repository;

    @Autowired
    public ImageService(ImageRepository repo){
        this.repository = repo;
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
                return criteriaBuilder.lessThan(root.get(Image_.publishedAt), LocalDate.now());
            }
        };
    }

    public List<Image> findAll() {
        List<Image> returnList = new ArrayList<>();
        repository.findAll().forEach(e -> returnList.add(e));
        return returnList;
    }

    public Image findById(int nr) {
        return repository.findById((long) nr).orElse(null);
    }
}
