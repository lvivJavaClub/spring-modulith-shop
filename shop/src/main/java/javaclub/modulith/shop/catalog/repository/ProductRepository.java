package javaclub.modulith.shop.catalog.repository;

import java.util.Optional;
import java.util.UUID;

import javaclub.modulith.shop.catalog.model.Product;

public interface ProductRepository {

	Product save(Product product);

	Optional<Product> findById(UUID productId);

}
