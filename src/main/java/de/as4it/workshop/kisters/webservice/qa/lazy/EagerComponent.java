package de.as4it.workshop.kisters.webservice.qa.lazy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class EagerComponent {

    @Autowired
    @Lazy
    LazyComponent lazyComponent;

    @PostConstruct
    void init() {
        log.info("Eager Component initialized");
    }

    public void useLazyComponent(){
        lazyComponent.doSomething();
    }



}
