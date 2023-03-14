package javaclub.modulith.shop.catalog.model;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductReview {

	private UUID id;

	private String reviewer;

	private int rate;

	private String review;

	private Instant createdAt;

}
