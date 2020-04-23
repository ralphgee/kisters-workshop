package de.as4it.workshop.kisters.webservice.repository;

import de.as4it.workshop.kisters.webservice.domain.Image;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;

//@RepositoryRestResource
public interface ImageRepository extends CrudRepository<Image, Long>, JpaSpecificationExecutor<Image> {

    public Image findAllByLocationContainsAndIdAfterOrderByName(String loc, int id);

    public Image findFirstByName(String name);

    public Image findImagesByPublishedAtAfter(Date publishDate);
    
}
