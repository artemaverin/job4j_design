create table job_position(
	position_id serial primary key,
	title text not NULL
);

create table employee (
    employee_id serial primary key,
	first_name text,
	last_name text,
	department varchar(255),
	position_id int references job_position(position_id)
);

insert into job_position (title) values ('manager');
insert into job_position (title) values ('hed of dep.');
insert into job_position (title) values ('specialist');
insert into job_position (title) values ('worker');

insert into employee (first_name, last_name, department, position_id) values ('Jack', 'Blak', 'customer service', 3);
insert into employee (first_name, last_name, department, position_id) values ('Tom', 'Hardy', 'warranty', 1);
insert into employee (first_name, last_name, department, position_id) values ('Sigurdsson', null, 'maintenance', 4);
insert into employee (first_name, last_name, department, position_id) values ('Ilon', 'Musk', 'administration', 2);
insert into employee (first_name, last_name, department, position_id) values ('Bruce', 'Li', 'legal service', 1);
insert into employee (first_name, last_name, department, position_id) values ('Billy', 'Jean', 'customer service', 1);
insert into employee (first_name, last_name, department, position_id) values ('Eva', 'Green', 'maintenance', 3);


select e.first_name, e.last_name, e.department, p.title
from job_position p join employee e on p.position_id = e.position_id
order by department;

select e.first_name, e.last_name, e.department, p.title from 
job_position p join employee e on p.position_id = e.position_id
where e.last_name is null;

select e.first_name, e.last_name, e.department, p.title from 
job_position p join employee e on p.position_id = e.position_id
where department like '%service';