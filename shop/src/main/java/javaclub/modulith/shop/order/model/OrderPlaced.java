package javaclub.modulith.shop.order.model;

import org.springframework.context.ApplicationEvent;

public class OrderPlaced extends ApplicationEvent {

    public OrderPlaced(Order source) {
        super(source);
    }

    public Order getOrder() {
        return (Order) super.getSource();
    }

}
