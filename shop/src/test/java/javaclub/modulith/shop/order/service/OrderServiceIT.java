package javaclub.modulith.shop.order.service;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import javaclub.modulith.shop.cart.Cart;
import javaclub.modulith.shop.cart.CartService;
import javaclub.modulith.shop.cart.Item;
import javaclub.modulith.shop.catalog.model.Product;
import javaclub.modulith.shop.catalog.service.CatalogService;
import javaclub.modulith.shop.order.model.OrderPlaced;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.AssertablePublishedEvents;
import org.springframework.modulith.test.PublishedEvents;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ApplicationModuleTest
class OrderServiceIT {

    @Autowired
    OrderService orderService;

    @MockBean
    private CartService cartService;

    @MockBean
    private CatalogService catalogService;

    @Test
    void place_new_order(PublishedEvents publishedEvents, AssertablePublishedEvents events) {
        var customerId = aCustomerId();
        mockCartWithProducts(
            customerId,
            aProduct("abc"),
            aProduct("zzz"),
            aProduct("xyz")
        );

        // when
        var order = orderService.placeOrder(customerId);

        // then
        assertThat(order.getOrderId()).isNotNull();

//        var matching = publishedEvents.ofType(OrderPlaced.class)
//            .matching(orderPlaced -> orderPlaced.getOrder().getCustomerId().equals(customerId));
//
//        assertThat(matching).hasSize(1);

        events.assertThat()
            .contains(OrderPlaced.class)
            .matching(orderPlaced -> orderPlaced.getOrder().getCustomerId().equals(customerId));
    }

    private void mockCartWithProducts(String customerId, Product... products) {
        var cart = Cart.builder()
            .customerId(customerId)
            .items(Arrays.stream(products)
                .map(p -> new Item().productId(p.getId()).count(2))
                .toList()
            ).build();
        when(cartService.findByCustomer(customerId)).thenReturn(Optional.of(cart));
        for (Product product : products) {
            when(catalogService.getProductById(product.getId())).thenReturn(Optional.of(product));
        }
    }

    private static String aCustomerId() {
        return UUID.randomUUID().toString();
    }

    private Product aProduct(String name) {
        return Product.builder()
            .id(UUID.randomUUID())
            .sku(UUID.randomUUID().toString())
            .name(name)
            .description("description of " + name)
            .price(10)
            .build();
    }

}
