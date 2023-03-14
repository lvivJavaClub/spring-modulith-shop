package javaclub.modulith.shop.catalog.adapter.jpa;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JavaType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.descriptor.java.UUIDJavaType;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "product")
public class ProductEntity {

	@Id
	@JavaType(UUIDJavaType.class)
	private UUID productId;

	private String sku;

	private String name;

	private String description;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<ProductReviewEntity> reviews;

	@CreationTimestamp
	private Instant createdAt;

	@UpdateTimestamp
	private Instant updatedAt;

}
