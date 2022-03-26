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

