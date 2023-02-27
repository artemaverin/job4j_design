CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers(first_name, last_name, age, country)
values
('Tom', 'Clancy', 43, 'USA'),
('Foma', 'Kinyaev', 28, 'Russia'),
('Yao', 'Ming', 35, 'China'),
('Haruhi', 'Suzumiya', 24, 'Japan'),
('Soul', 'Goodman', 24, 'USA');

select first_name, last_name from customers
where age = (select min(age) from customers);