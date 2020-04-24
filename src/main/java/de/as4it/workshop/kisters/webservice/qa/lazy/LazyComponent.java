package de.as4it.workshop.kisters.webservice.qa.lazy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Lazy
@Slf4j
@Component
public class LazyComponent {

    @PostConstruct
    void init() {
        log.info("Lazy Component initialized");
    }

    void doSomething(){
        log.info("Do somthing");
    }

}
