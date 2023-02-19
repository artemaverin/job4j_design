create table categories(
	category_id serial primary key,
	cat_name text
);

create table products(
	product_id serial primary key,
	category_id int references categories(category_id),
	prod_name text
);

create table product_price (
	id serial primary key,
	product_id int references products(product_id),
	price decimal
);

insert into categories(cat_name)
values
('бытовая техника'),
('телефоны');

insert into products(category_id, prod_name)
values
(1, 'пылесос'),
(1, 'холодильник'),
(1, 'микроволновка'),
(2, 'iphone 4'),
(2, 'nokia 3310'),
(2, 'samsung galaxy'),
(2, 'xiaomi');

insert into product_price(product_id, price)
values
(1, 15600),
(2, 33500),
(3, 21400),
(4, 51000),
(6, 68000),
(7, 49999);

-- обычный запрос
select c.cat_name as name, 
max(pp.price) as max_price,
min(pp.price) as min_price,
round(avg(pp.price),0) as avg_price
from categories c
join products p on c.category_id = p.category_id
join product_price pp on pp.product_id = p.product_id
group by c.cat_name

--запрос с представлением
create view category_price 
as select c.cat_name as name, 
max(pp.price) as max_price,
min(pp.price) as min_price,
round(avg(pp.price),0) as avg_price
from categories c
join products p on c.category_id = p.category_id
join product_price pp on pp.product_id = p.product_id
group by c.cat_name;

select * from category_price;
