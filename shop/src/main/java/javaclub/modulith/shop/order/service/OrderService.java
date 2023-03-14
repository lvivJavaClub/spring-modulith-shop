package javaclub.modulith.shop.order.service;


import javaclub.modulith.shop.cart.Cart;
import javaclub.modulith.shop.cart.CartService;
import javaclub.modulith.shop.cart.Item;
import javaclub.modulith.shop.catalog.service.CatalogService;
import javaclub.modulith.shop.order.model.Order;
import javaclub.modulith.shop.order.model.OrderPlaced;
import javaclub.modulith.shop.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final CartService carts;

    private final CatalogService catalog;

    private final OrderRepository orderRepository;

    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public Order placeOrder(String customerId) {
        log.info("Place new Order [customerId={}]", customerId);
        var cart = carts.findByCustomer(customerId)
            .orElseThrow(() -> new IllegalStateException("Customer's Cart is Empty. Id=%s".formatted(customerId)));
        var sourceOrder = newOrderForCart(cart);
        var order = orderRepository.save(sourceOrder);
//        eventPublisher.publishEvent(new OrderPlaced(order));
        return order;
    }

    private Order newOrderForCart(Cart cart) {
        var order = Order.newOrderForCustomer(cart.getCustomerId());
        for (Item cartItem : cart.getItems()) {
            var product = catalog.getProductById(cartItem.productId())
                .orElseThrow(() -> new IllegalStateException(
                    "Invalid Product in the Cart id=%s".formatted(cartItem.productId()))
                );
            order.addOrderItem(product, cartItem.count());
        }
        return order;
    }

}
