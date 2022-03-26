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

create table addresses (
    id integer not null
            constraint addresses_pk
    primary key,
    street varchar(255),
    city varchar(100),
    county varchar(100),
    postal_code varchar(15)
);

alter table addresses
    owner to postgres;

create table store_user(
    id integer not null
       constraint user_pk primary key,
    username varchar(100) not null,
    first_name   varchar(50),
    last_name    varchar(100),
    activation_code varchar(255),
    active boolean not null,
    email varchar(100),
    password varchar(100) not null
);

alter table store_user
    owner to postgres;

create unique index store_user_id_uindex
    on store_user (id);