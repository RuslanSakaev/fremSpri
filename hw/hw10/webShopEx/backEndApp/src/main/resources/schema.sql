CREATE TABLE IF NOT EXISTS product ( -- Создание таблицы "product", если она не существует
    id BIGSERIAL PRIMARY KEY, -- Поле "id" типа BIGSERIAL (автоматически инкрементируемый) и первичный ключ
    name VARCHAR(255), -- Поле "name" типа VARCHAR с максимальной длиной 255 символов
    description VARCHAR(255), -- Поле "description" типа VARCHAR с максимальной длиной 255 символов
    price FLOAT NOT NULL, -- Поле "price" типа FLOAT, не может содержать значение NULL
    quantity INTEGER NOT NULL, -- Поле "quantity" типа INTEGER, не может содержать значение NULL
    RESERVED_QUANTITY INTEGER DEFAULT 0 -- Поле "RESERVED_QUANTITY" типа INTEGER со значением по умолчанию 0
);
