CREATE TABLE IF NOT EXISTS Customer (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    phone BIGINT,
    email VARCHAR(100),
    address VARCHAR(255)
);
Create table if not exists Product(
 id int primary key,
 name varchar(20) not null,
 price double not null,
 gst_percentage double not null,
 stock_quantity int not null
 )
