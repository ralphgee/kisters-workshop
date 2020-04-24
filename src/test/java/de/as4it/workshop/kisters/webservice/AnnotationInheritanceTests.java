package de.as4it.workshop.kisters.webservice;

import de.as4it.workshop.kisters.webservice.qa.ChildComponent;
import de.as4it.workshop.kisters.webservice.qa.ComponentWithInheritance;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnnotationInheritanceTests {

    @Test
    public void testIfChildClassInheritesAnnotationFromParent()
    {
        Assertions.assertThat(ChildComponent.class.getAnnotation(ComponentWithInheritance.class)).isNotNull();
    }
}
