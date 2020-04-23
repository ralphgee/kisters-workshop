package de.as4it.workshop.kisters.webservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ImageAsyncService {

    @Async("WebserviceAsyncExecutor")
    public void delegateLongRunningTask() {
        log.info("Running Task asychronuously");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Finished Running Task asychronuously");
    }
}
