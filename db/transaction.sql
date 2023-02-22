create table courses(
	id serial primary key,
	c_name text,
	course_price int,
	hours int
);

insert into courses(c_name,course_price,hours)
values
('Java', 150000, 200),
('Python', 125000, 125),
('php', 99000, 165);
