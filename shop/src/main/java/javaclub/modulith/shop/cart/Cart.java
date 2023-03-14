package javaclub.modulith.shop.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javaclub.modulith.shop.catalog.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Cart {

    private UUID cartId;

    private String customerId;

    private List<Item> items;

    public static Cart newCartForCustomer(String customerId) {
        return Cart.builder()
            .cartId(UUID.randomUUID())
            .customerId(customerId)
            .items(new ArrayList<>())
            .build();
    }

    public void addProduct(Product product, int count) {
        items.add(
            new Item().productId(product.getId()).count(count)
        );
    }
}
