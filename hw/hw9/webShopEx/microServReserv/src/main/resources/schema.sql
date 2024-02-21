CREATE TABLE IF NOT EXISTS product (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    price FLOAT NOT NULL,
    quantity INTEGER NOT NULL,
    RESERVED_QUANTITY INTEGER DEFAULT 0
);

CREATE TABLE IF NOT EXISTS reservation (
    id BIGSERIAL PRIMARY KEY,
    reservation_id VARCHAR(255),
    item_id VARCHAR(255),
    quantity INTEGER NOT NULL,
    product_id BIGINT,
    created_at TIMESTAMP
);
