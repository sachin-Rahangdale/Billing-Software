DROP TABLE IF EXISTS invoice_item;
DROP TABLE IF EXISTS invoice;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Customer;

CREATE TABLE Customer (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    phone BIGINT,
    email VARCHAR(100),
    address VARCHAR(255)
);

CREATE TABLE Product (
    id INT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    price DOUBLE NOT NULL,
    gst_percentage DOUBLE NOT NULL,
    stock_quantity INT NOT NULL
);

CREATE TABLE invoice (
    invoice_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    invoice_date DATE NOT NULL,
    customer_id INT,  -- MUST match Customer.id (INT)
    total_amount DOUBLE NOT NULL,
    total_tax DOUBLE NOT NULL,
    discount DOUBLE NOT NULL,
    final_amount DOUBLE NOT NULL,

    CONSTRAINT fk_invoice_customer
        FOREIGN KEY (customer_id)
        REFERENCES Customer(id)
);

CREATE TABLE invoice_item (
    item_id BIGINT PRIMARY KEY AUTO_INCREMENT,

    invoice_id BIGINT NOT NULL,
    product_id INT NOT NULL,

    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    tax_amount DOUBLE NOT NULL,
    discount DOUBLE NOT NULL,
    total DOUBLE NOT NULL,

    CONSTRAINT fk_invoice
        FOREIGN KEY (invoice_id)
        REFERENCES invoice(invoice_id),

    CONSTRAINT fk_product
        FOREIGN KEY (product_id)
        REFERENCES Product(id)
);
