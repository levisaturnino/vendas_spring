CREATE TABLE Client(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100)
);

CREATE TABLE Product(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(100),
    price NUMERIC(20,2)
);

CREATE TABLE Orders(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    client_id INTEGER REFERENCES Client(id),
    data_order TIMESTAMP,
    total NUMERIC(20,2)
);


CREATE TABLE item_order(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    order_id INTEGER REFERENCES Orders(id),
    product_id INTEGER REFERENCES Product(id),
    quantity INTEGER
);