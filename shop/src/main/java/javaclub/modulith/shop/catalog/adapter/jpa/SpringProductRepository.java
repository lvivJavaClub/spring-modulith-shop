package javaclub.modulith.shop.catalog.adapter.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

@Component
public interface SpringProductRepository extends Repository<ProductEntity, UUID> {

	ProductEntity save(ProductEntity source);

	Optional<ProductEntity> findByProductId(UUID productId);

}
