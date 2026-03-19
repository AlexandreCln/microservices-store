package fr.microservices_store.catalog_service.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Partial integration test that will use only entities, repositories, ORM config and start a database.
// Also, each test is run within a transaction.
@DataJpaTest(
        properties = {
            // Disable default H2 in-memory database
            "spring.test.database.replace=none",
            // Instead, starts a PostgreSQL database using Testcontainers (tc)
            "spring.datasource.url=jdbc:tc:postgresql:18-alpine:///db",
        }
)
@Sql("/test-data.sql")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldGetAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        assertThat(products).hasSize(15);
    }
}
