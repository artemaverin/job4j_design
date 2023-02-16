create table employee (
    employee_id SERIAL primary key,
	first_name text,
	last_name text,
	department varchar(255)
);

insert into employee(first_name, last_name, department)
values('Bob', 'Marley', 'Account');

update employee
set department = 'Finance';

delete from employee;

