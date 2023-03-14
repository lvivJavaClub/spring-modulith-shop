package javaclub.modulith.shop.order.service;

import org.springframework.context.event.EventListener;
import org.springframework.modulith.moments.DayHasPassed;
import org.springframework.stereotype.Component;

@Component
public class DailyDigest {

    @EventListener
    void dailyDigest(DayHasPassed dayHasPassed) {
        var date = dayHasPassed.getDate();
        System.out.println("day has passed " + date);
    }

}
