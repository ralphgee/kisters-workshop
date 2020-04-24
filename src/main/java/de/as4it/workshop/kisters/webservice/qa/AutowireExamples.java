package de.as4it.workshop.kisters.webservice.qa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class AutowireExamples {

    String StringByConstructor;
    @Autowired //uses Constructor Parameter
    public AutowireExamples(String s3) {
        this.StringByConstructor = s3;
    }
    @Autowired
    String s1;

    @Autowired //uses method Parameter!
    public void setStringBySetter(String s2){
        this.StringBySetter = s2;
    }

    @PostConstruct
    private void showString(){
        log.info("String s1: {}", s1);
        log.info("String StringBySetter: {}", StringBySetter);
        log.info("String AddStringBySetter: {}", AddStringBySetter);
        log.info("String StringByConstrcutor: {}", StringByConstructor);
    }

    private String StringBySetter;
    private String AddStringBySetter;

    @Autowired
    public void setAdd(String s2) {
        AddStringBySetter = s2;
    }
}
