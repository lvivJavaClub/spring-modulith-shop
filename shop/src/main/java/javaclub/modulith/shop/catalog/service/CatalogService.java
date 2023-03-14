package javaclub.modulith.shop.catalog.service;

import java.util.Optional;
import java.util.UUID;

import javaclub.modulith.shop.catalog.model.Product;
import javaclub.modulith.shop.catalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CatalogService {

    private final ProductRepository productRepository;

    public Optional<Product> getProductById(UUID productId) {
        return productRepository.findById(productId);
    }

}
