CREATE TABLE orders
(
    order_id    VARCHAR(36) PRIMARY KEY,
    customer_id VARCHAR(36) NOT NULL,
    status       VARCHAR(40) NOT NULL,
    total       integer     NOT NULL,
    created_at  timestamp,
    updated_at  timestamp
);

CREATE TABLE order_item
(
    item_id     VARCHAR(36) PRIMARY KEY,
    order_ref   VARCHAR(36) NOT NULL REFERENCES orders (order_id),
    product_ref VARCHAR(36) NOT NULL,
    count       integer     NOT NULL,
    price       integer     NOT NULL,
    total       integer     NOT NULL
);

create index idx_order_item ON order_item (order_ref);