package javaclub.modulith.shop.catalog.model;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Product {

	private UUID id;

	private String sku;

	private String name;

	private String description;

	private int price;

	private List<ProductReview> reviews;

	private Instant createdAt;

	private Instant updatedAt;

}
