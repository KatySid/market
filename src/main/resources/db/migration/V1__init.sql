BEGIN;
create table users (
    id                      bigserial primary key,
    username                varchar(30) not null unique,
    password                varchar(80) not null,
    email                   varchar(80) unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create table roles (
    id                      bigserial primary key,
    name                    varchar(50) not null unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

CREATE TABLE users_roles (
          user_id                 bigint not null references users (id),
          role_id                 bigint not null references roles (id),
          primary key (user_id, role_id)
);



insert into roles (name)
values
('ROLE_USER'),
('ROLE_ADMIN');

insert into users (username, password, email)
values
('user', '$2y$12$4g1SOm4vGFSF/CbT84nOzOyygKSuTtRshecj7HYOCC1xUPjhkVPWG', 'bob_johnson@gmail.com'),
('admin', '$2y$12$4g1SOm4vGFSF/CbT84nOzOyygKSuTtRshecj7HYOCC1xUPjhkVPWG', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);
DROP TABLE IF EXISTS categories CASCADE;
CREATE TABLE categories (
id                                  bigserial PRIMARY KEY,
title                               VARCHAR(255),
created_at                          TIMESTAMP DEFAULT current_timestamp,
updated_at                          TIMESTAMP DEFAULT current_timestamp
);
INSERT INTO categories (title) VALUES
('food'),
('clothes');

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (
id                                  bigserial PRIMARY KEY,
title                               VARCHAR(255),
price                               numeric(8,2),
category_id                         bigint REFERENCES categories (id),
created_at                          TIMESTAMP DEFAULT current_timestamp,
updated_at                          TIMESTAMP DEFAULT current_timestamp
);
INSERT INTO products (title, price, category_id) VALUES
('Bread', 25.50, 1),
('Milk', 80.56, 1),
('Dress', 500.35, 2),
('Bread1', 25, 1),
('Milk1', 80, 1),
('Dress1', 500, 2),
('Bread2', 25.50, 1),
('Milk2', 80.56, 1),
('Dress2', 500.35, 2),
('Bread3', 25, 1),
('Milk3', 80, 1),
('Dress3', 500, 2);



create table orders (
    id                              bigserial primary key,
    user_id                         bigint references users (id),
    price                           numeric (8, 2),
    adress                          varchar(255),
    phone_Number                     varchar(255),
    created_at                      timestamp default current_timestamp,
    updated_at                      timestamp default current_timestamp
);
CREATE TABLE order_items (
id                                  bigserial PRIMARY KEY,
product_id                          bigint REFERENCES products (id),
order_id                            bigint references orders (id),
quantity                            int,
price_per_product                   numeric(8,2),
price                               numeric(8,2),
adress                              varchar(255),
phone_Number                        varchar(255),
created_at                          TIMESTAMP DEFAULT current_timestamp,
updated_at                          TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE reviews (
    id                      bigserial primary key,
    title                   VARCHAR(255),
    user_id                 bigint not null references users (id),
    product_id              bigint not null references products (id),
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

COMMIT;