package fr.microservices_store.catalog_service;

import fr.microservices_store.catalog_service.domain.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
// Enable reading of the application.properties file by mapping it to a class or record.
// You can also automatically enable all config properties with @ConfigurationPropertiesScan
@EnableConfigurationProperties(ApplicationProperties.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
