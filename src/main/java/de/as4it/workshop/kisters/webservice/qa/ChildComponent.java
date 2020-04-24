package de.as4it.workshop.kisters.webservice.qa;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Slf4j
public class ChildComponent extends ParentComponent {

    @PostConstruct
    public void init(){
        log.error("CHILD INIT");
    }

}
