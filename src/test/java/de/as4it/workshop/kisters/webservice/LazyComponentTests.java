package de.as4it.workshop.kisters.webservice;

import de.as4it.workshop.kisters.webservice.qa.lazy.EagerComponent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LazyComponentTests {

    @Autowired
    EagerComponent eagerComponent;

    @Test
    public void testLazyComponent() {
        eagerComponent.useLazyComponent();
    }
}
