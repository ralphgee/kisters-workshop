package de.as4it.workshop.kisters.webservice.qa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@ComponentWithInheritance
@Slf4j
public class ParentComponent {

    @PostConstruct
    public void init(){
        log.error("PARENT INIT");
    }
}
