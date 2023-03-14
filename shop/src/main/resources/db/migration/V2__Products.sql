CREATE TABLE product
(
    product_id  VARCHAR(36) PRIMARY KEY,
    sku         VARCHAR(50) NOT NULL,
    name        TEXT        NOT NULL,
    description TEXT        NOT NULL,
    created_at  timestamp   NOT NULL,
    updated_at  timestamp   NOT NULL
);

CREATE TABLE product_review
(
    review_id   VARCHAR(36) PRIMARY KEY,
    product_ref VARCHAR(36) NOT NULL,
    reviewer    TEXT        NOT NULL,
    rate        int,
    review      TEXT        NOT NULL,
    created_at  timestamp   NOT NULL,
    CONSTRAINT fk_product_ref FOREIGN KEY (product_ref) REFERENCES product (product_id)
);

CREATE INDEX idx_product_review ON product_review (product_ref);
