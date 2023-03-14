package javaclub.modulith.shop.cart;

import java.util.Optional;
import java.util.UUID;

import javaclub.modulith.shop.catalog.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CartService {
    public Optional<Cart> findById(UUID cartId) {
        return Optional.empty();
    }

    private final CatalogService catalogService;

    public Optional<Cart> findByCustomer(String customerId) {
        return Optional.empty();
    }

    @Transactional
    public Cart addProductToCart(String customerId, UUID productId, int count) {
        var cart = findByCustomer(customerId)
            .orElseGet(() -> Cart.newCartForCustomer(customerId));
        var product = catalogService.getProductById(productId)
            .orElseThrow(() -> new IllegalStateException("Invalid Product" + productId));
        cart.addProduct(product, count);
        return cart;
    }

}
