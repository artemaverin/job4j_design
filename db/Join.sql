create table departments (
	id serial primary key,
	dep_name varchar(255)
);

create table employees (
	id serial primary key,
	emp_name varchar(255),
	dep_id int references departments(id)
);

create table teens(
	id serial primary key,
	teen_name varchar(255),
	gender varchar(255)
);

insert into teens(teen_name, gender)
values
('Sam', 'male'),
('Alan', 'male'),
('Elena', 'female'),
('John', 'male'),
('Kristina', 'female'),
('Max', 'male'),
('Buffy', 'female');

insert into departments(dep_name)
values
('legal'),
('finance'),
('customer service'),
('it');

insert into employees (emp_name, dep_id)
values
('Sarah', 1),
('Peter', 4),
('Gomer', 2),
('Tom', 1),
('Ben', 2),
('David', 4),
('Anna', 1);

--select queries

select * from departments d left join employees e
on d.id = e.dep_id;

select * from departments d right join employees e
on d.id = e.dep_id;

select * from departments d cross join employees e;

select distinct d.dep_name from departments d left join employees e
on d.id = e.dep_id
where e.dep_id is null;

select distinct d.dep_name, count(distinct e.emp_name) 
from departments d left join employees e
on d.id = e.dep_id
group by d.dep_name;

select distinct d.dep_name, count(distinct e.emp_name) 
from employees e right join departments d
on d.id = e.dep_id
group by d.dep_name;

select t1.teen_name, t2.teen_name from teens t1 cross join teens t2
where t1.gender != t2.gender;