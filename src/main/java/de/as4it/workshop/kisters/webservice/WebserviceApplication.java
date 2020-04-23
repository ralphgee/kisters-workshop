package de.as4it.workshop.kisters.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication
@ServletComponentScan
@EnableJpaRepositories
//@EnableJpaRepositories(basePackages = "de.as4it.workshop.kisters.webservice.repository",entityManagerFactoryRef = "entityManagerFactory2")
@EnableScheduling
public class WebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebserviceApplication.class, args);
	}

//	@Bean
//	public EntityManagerFactory entityManagerFactory2(EntityManagerFactoryBuilder builder){
//		//builder.dataSource(DataSourceBuilder.create().)
//
//		return null;
//	}
	
}
