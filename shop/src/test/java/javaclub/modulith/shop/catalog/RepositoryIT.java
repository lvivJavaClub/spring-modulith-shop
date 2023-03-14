package javaclub.modulith.shop.catalog;

import java.util.UUID;

import javaclub.modulith.shop.catalog.model.Product;
import javaclub.modulith.shop.catalog.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RepositoryIT {

	@Autowired
	private ProductRepository productRepository;

	@Test
	void save_new_product() {
		var source = Product.builder()
			.id(UUID.randomUUID())
			.sku(UUID.randomUUID().toString())
			.name("save_new_product")
			.description("description for save_new_product")
			.build();

		var product = productRepository.save(source);

		assertThat(product.getCreatedAt()).isNotNull();
		assertThat(product.getUpdatedAt()).isNotNull();
		assertThat(product.getReviews()).isNotNull();
	}

}
