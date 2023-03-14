package javaclub.modulith.shop.order.web;

import java.util.UUID;

import javaclub.modulith.shop.order.model.Order;
import javaclub.modulith.shop.order.service.OrderService;
import javaclub.modulith.shop.order.spi.PlaceOrderReponse;
import javaclub.modulith.shop.order.spi.PlaceOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    PlaceOrderReponse placeNewOrder(@RequestBody PlaceOrderRequest request) {
        var customerId = UUID.randomUUID().toString();
        var order = orderService.placeOrder(customerId);
        return new PlaceOrderReponse(order.getOrderId());
    }

}
