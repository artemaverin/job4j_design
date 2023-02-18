create table position(
	position_id serial primary key,
	title text not NULL
);

create table employee (
    employee_id SERIAL primary key,
	first_name text,
	last_name text,
	department varchar(255),
	position_id int references position(position_id)
);

insert into position (title)
values
('manager'),
('hed of dep.'),
('specialist'),
('worker');

insert into employee (first_name, last_name, department, position_id)
values
('Jack', 'Blak', 'customer service', 3),
('Tom', 'Hardy', 'warranty', 1),
('Sigurdsson', null, 'maintenance', 4),
('Ilon', 'Musk', 'administration', 2),
('Bruce', 'Li', 'legal service', 1),
('Billy', 'Jean', 'customer service', 1),
('Eva', 'Green', 'maintenance', 3);

/**
select e.first_name, e.last_name, e.department, p.title from 
position p join employee e on p.position_id = e.position_id
order by department

select e.first_name, e.last_name, e.department, p.title from 
position p join employee e on p.position_id = e.position_id
where e.last_name is null

select e.first_name, e.last_name, e.department, p.title from 
position p join employee e on p.position_id = e.position_id
where department like '%service'