create table customers (
	customer_id serial primary key,
	customer_name char(50) not NULL
);

create table orders(
	order_id serial primary key,
	customer_id int references customers(customer_id),
	order_date date not NULL
);

