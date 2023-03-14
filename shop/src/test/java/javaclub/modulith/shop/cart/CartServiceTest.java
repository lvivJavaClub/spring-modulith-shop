package javaclub.modulith.shop.cart;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.modulith.test.ApplicationModuleTest.BootstrapMode.DIRECT_DEPENDENCIES;

@ApplicationModuleTest(DIRECT_DEPENDENCIES)
class CartServiceTest {

    @Autowired
    CartService cartService;

    @Test
    void test_add_product_to_cart() {
        var productId = UUID.randomUUID();
        var customerId = UUID.randomUUID().toString();

        // when
        assertThatThrownBy(() -> cartService.addProductToCart(customerId, productId, 1))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Invalid Product");
    }

}
