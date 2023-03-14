package javaclub.modulith.shop.loyalty.listener;

import javaclub.modulith.shop.loyalty.LoyaltyService;
import javaclub.modulith.shop.order.model.OrderPlaced;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class OrderPlacedListener {

    private final LoyaltyService loyaltyService;

    @Transactional
    @TransactionalEventListener
    void onNewOrderPlaced(OrderPlaced orderPlaced) {
        loyaltyService.collectBonusesForNewOrder(orderPlaced.getOrder());
    }

}
