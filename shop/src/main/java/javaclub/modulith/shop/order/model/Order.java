package javaclub.modulith.shop.order.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import javaclub.modulith.shop.catalog.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JavaType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.descriptor.java.UUIDJavaType;
import org.springframework.data.domain.AbstractAggregateRoot;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "orders")
public class Order extends AbstractAggregateRoot<Order> {

    @Id
    @JavaType(UUIDJavaType.class)
    private UUID orderId;

    private String customerId;

    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    private long total;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public static Order newOrderForCustomer(String customerId) {
        var order = Order.builder()
            .orderId(UUID.randomUUID())
            .status(OrderStatus.NEW)
            .customerId(customerId).build();
        order.registerEvent(new OrderPlaced(order));
        return order;
    }

    public void addOrderItem(Product product, int count) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(buildOrderItem(product, count));
    }

    private OrderItem buildOrderItem(Product product, int count) {
        var orderItem = new OrderItem();
        orderItem.setItemId(UUID.randomUUID());
        orderItem.setOrder(this);
        orderItem.setProductRef(product.getId());
        orderItem.setCount(count);
        orderItem.setPrice(product.getPrice());
        orderItem.setTotal(count * product.getPrice());
        total += orderItem.getTotal();
        return orderItem;
    }

}
