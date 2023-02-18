create table type(
	id serial primary key,
	type_name text
);

create table product(
	product_id serial primary key,
	prod_name text not NULL,
	type_id int references type(id),
	expired_date date,
	price decimal
);

insert into type(type_name)
values
('СЫР'),
('МОЛОКО'),
('МЯСО'),
('ХЛЕБ'),
('ОВОЩИ');

insert into product(prod_name, type_id, expired_date, price)
values
('мороженое в шоколаде', 2, '2023-05-31', 15.65),
('сыр плавленный', 1, '2023-01-30', 54.90),
('замороженое куриное мясо', 3, '2024-01-30', 199.99),
('лаваш', 4, '2021-11-20', 9.99),
('огурцы', 5, '2021-11-20', 21.30),
('бри', 1, '2023-09-03', 78.55),
('багет', 4, '2023-04-18', 12.38),
('бекон', 3, '2023-02-28', 89.06),
('колбасный сыр', 1, '2023-12-15', 50.00),
('красный перец', 5, '2022-12-31', 10.50),
('сметана', 2, '2023-10-10', 19.14),
('Босфор', 1, '2023-07-29', 101.94),
('говядина', 3, '2023-03-12', 200.99),
('булка с маком', 4, '2023-04-14', 5.64),
('лук', 5, '2023-05-19', 7.85),
('сыр с плесенью', 1, '2023-07-30', 71.41),
('кекс', 4, '2023-10-05', 25.00),
('косичка', 1, '2023-10-17', 31.90),
('свинина', 3, '2023-11-09', 141.72),
('пирог', 4, '2023-12-22', 49.88),
('овощное рагу - замороженое', 5, '2025-01-19', 64.10),
('капуста', 5, '2023-04-19', 11.05),
('майонез', 2, '2023-12-29', 24.17),
('пармезан', 1, '2023-05-19', 83.00),
('моцарелла', 1, '2024-05-19', 73.00),
('риккота', 1, '2024-10-22', 55.08),
('белый хлеб', 4, '2023-04-04', 15.85),
('черный хлеб', 4, '2023-04-04', 12.12),
('круассан', 4, '2023-06-14', 19.56),
('бородинский хлеб', 4, '2023-08-24', 24.73),
('батон', 4, '2023-09-05', 17.15),
('брынза', 1, '2023-02-05', 99.99);


select p.prod_name from product p join type t
on p.type_id = t.id
where t.type_name = 'СЫР';

select prod_name from product
where prod_name like '%мороженое%';

select prod_name from product
where expired_date < now();

select prod_name from product
where price = (select max(price) from product);

select t.type_name, count(distinct prod_name) as prod_count from product p join type t
on p.type_id = t.id
group by t.type_name;

select p.prod_name from product p join type t
on p.type_id = t.id
where t.type_name in ('СЫР', 'МОЛОКО');

select t.type_name from product p join type t
on p.type_id = t.id
group by t.type_name
having count(distinct prod_name) < 10;

select t.type_name, p.prod_name  from product p join type t
on p.type_id = t.id
order by t.type_name, p.prod_name;