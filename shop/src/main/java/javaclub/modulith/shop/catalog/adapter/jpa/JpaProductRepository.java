package javaclub.modulith.shop.catalog.adapter.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javaclub.modulith.shop.catalog.model.Product;
import javaclub.modulith.shop.catalog.model.ProductReview;
import javaclub.modulith.shop.catalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class JpaProductRepository implements ProductRepository {

    private final SpringProductRepository springProductRepository;

    @Override
    public Product save(Product product) {
        var source = fromDomain(product);
        return toDomain(springProductRepository.save(source));
    }

    @Override
    public Optional<Product> findById(UUID productId) {
        return springProductRepository.findByProductId(productId).map(JpaProductRepository::toDomain);
    }

    private ProductEntity fromDomain(Product product) {
        return ProductEntity.builder()
            .productId(product.getId())
            .sku(product.getSku())
            .name(product.getName())
            .description(product.getDescription())
            .createdAt(product.getCreatedAt())
            .updatedAt(product.getUpdatedAt())
            .build();
    }

    private static Product toDomain(ProductEntity productEntity) {
        return Product.builder()
            .id(productEntity.getProductId())
            .sku(productEntity.getSku())
            .name(productEntity.getName())
            .description(productEntity.getDescription())
            .reviews(toDomain(productEntity.getReviews()))
            .createdAt(productEntity.getCreatedAt())
            .updatedAt(productEntity.getUpdatedAt())
            .build();
    }

    private static List<ProductReview> toDomain(List<ProductReviewEntity> reviews) {
        if (reviews == null) {
            return new ArrayList<>();
        }
        return reviews.stream().map(JpaProductRepository::toDomain).toList();
    }

    private static ProductReview toDomain(ProductReviewEntity productReviewEntity) {
        return ProductReview.builder()
            .id(productReviewEntity.getReviewId())
            .reviewer(productReviewEntity.getReviewer())
            .rate(productReviewEntity.getRate())
            .createdAt(productReviewEntity.getCreatedAt())
            .build();
    }

}
