
create table addresses
(
    id          integer not null
        constraint addresses_pk
            primary key,
    street      varchar(255),
    city        varchar(100),
    county      varchar(100),
    postal_code varchar(15)
);

alter table addresses
    owner to postgres;

CREATE SEQUENCE USER_SEQ increment by 1;

create table store_user
(
    id              integer      not null
        constraint user_pk primary key,
    username        varchar(100) not null,
    first_name      varchar(50),
    last_name       varchar(100),
    activation_code varchar(255),
    active          boolean      not null,
    email           varchar(100),
    password        varchar(100) not null
);

alter table store_user
    owner to postgres;

create unique index store_user_id_uindex
    on store_user (id);

create table products
(
    id serial constraint product_pk primary key,
    title              varchar(50) not null,
    description        varchar(255),
    brand              varchar(100),
    volume             varchar(20),
    price              float4      not null,
    product_type       varchar(255),
    year               int4        not null,
    product_base_notes varchar(255),
    image_url          varchar(255)
);

alter table products
    owner to postgres;

create unique index product_id_index
    on products (id);



create table orders
(
    id           integer not null
        constraint orders_pk
            primary key,
    first_name   varchar(50),
    last_name    varchar(100),
    phone_number varchar(15),
    order_date   date,
    user_id      integer,
    address_id   integer
);

alter table orders
    owner to postgres;

create unique index orders_id_uindex
    on orders (id);

create table order_item
(
    product_id integer not null
        constraint product_item_fk references products
            on update set null,
    order_id   integer not null
        constraint order_fk references orders (id)
            on update set null,
    user_id    integer not null
        constraint user_fk references store_user (id)
            on update set null
);

alter table order_item
    owner to loauhxaxhnoppe;

ALTER TABLE ADDRESSES
ALTER COLUMN ID
ADD GENERATED ALWAYS AS IDENTITY;

ALTER TABLE ORDERS
ALTER COLUMN ID
ADD GENERATED ALWAYS AS IDENTITY;

ALTER TABLE ORDER_ITEM
ADD COLUMN ID serial constraint order_item_pk PRIMARY KEY;

ALTER TABLE orders
ADD TOTAL float;
