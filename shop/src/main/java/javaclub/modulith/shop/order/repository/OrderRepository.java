package javaclub.modulith.shop.order.repository;

import java.util.UUID;

import javaclub.modulith.shop.order.model.Order;
import org.springframework.data.repository.Repository;

public interface OrderRepository extends Repository<Order, UUID> {

    Order save(Order source);

}
