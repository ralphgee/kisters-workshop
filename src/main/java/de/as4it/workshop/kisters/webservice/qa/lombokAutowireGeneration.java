package de.as4it.workshop.kisters.webservice.qa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class lombokAutowireGeneration {
    public final ApplicationContext ctx;

    @PostConstruct
    private void init() {
        log.info("Application {} availeble", ctx.getDisplayName());
    }
}
