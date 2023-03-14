package javaclub.modulith.shop.order.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JavaType;
import org.hibernate.type.descriptor.java.UUIDJavaType;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class OrderItem {

    @Id
    @JavaType(UUIDJavaType.class)
    private UUID itemId;

    @ManyToOne
    @JoinColumn(name = "order_ref")
    private Order order;

    @JavaType(UUIDJavaType.class)
    private UUID productRef;

    private int count;

    private int price;

    private int total;

}
