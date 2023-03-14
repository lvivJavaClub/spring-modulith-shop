package javaclub.modulith.shop.cart;

import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true, chain = true)
public class Item {

    private UUID productId;

    private int count;

}
