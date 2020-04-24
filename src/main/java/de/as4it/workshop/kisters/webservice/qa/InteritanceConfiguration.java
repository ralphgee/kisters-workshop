package de.as4it.workshop.kisters.webservice.qa;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "de.as4it.workshop.kisters.webservice.qa",
        includeFilters = @ComponentScan.Filter(ComponentWithInheritance.class))
public class InteritanceConfiguration {

}
