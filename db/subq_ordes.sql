CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders(amount, customer_id)
values
(20, 1),
(35, 3),
(40, 1),
(66, 5);

select c.first_name, c.last_name from customers c
where c.id NOT IN (select ord.customer_id from orders ord);