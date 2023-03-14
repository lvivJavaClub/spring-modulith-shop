package javaclub.modulith.shop.catalog.adapter.jpa;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JavaType;
import org.hibernate.type.descriptor.java.UUIDJavaType;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "product_review")
public class ProductReviewEntity {

	@Id
	@JavaType(UUIDJavaType.class)
	private UUID reviewId;

	@ManyToOne
	@JoinColumn(name = "product_ref")
	private ProductEntity product;

	private String reviewer;

	private int rate;

	private String review;

	@CreationTimestamp
	private Instant createdAt;

}
