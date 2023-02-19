create table car_bodies(
	id serial primary key,
	body_name char(25)
);

create table car_engines(
	id serial primary key,
	engine_name char(25)
);

create table car_transmissions(
	id serial primary key,
	transm_name char(25)
);

create table cars(
	id serial primary key,
	car_name char(25),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies (body_name)
values
('hatchback'),
('sedan'),
('сoupe'),
('wagon'),
('van');

insert into car_engines (engine_name)
values
('diesel'),
('electric'),
('petrol'),
('hybrid');

insert into car_transmissions(transm_name)
values
('manual'),
('variable'),
('automatic');

insert into cars(car_name, body_id, engine_id, transmission_id)
values
('mazda-3', 1, null, 3),
('skoda superb', 2, 3, 1),
('chevrolet camaro IV', 3, 3, 3),
('volkswagen passat B6', 4, 1, 1),
('kia optima IV', null, 1, null),
('tesla model 3', null, 2, 3);

--select queries
select c.id, c.car_name, cb.body_name, ce.engine_name, ct.transm_name 
from cars c 
left join car_bodies cb on c.body_id = cb.id
left join car_engines ce on c.engine_id = ce.id
left join car_transmissions ct on c.transmission_id = ct.id;

select cb.body_name from cars c
right join car_bodies cb on c.body_id = cb.id
where c.body_id is null;

select ce.engine_name from cars c
right join car_engines ce on c.engine_id = ce.id
where c.engine_id is null;

select ct.transm_name from cars c
right join car_transmissions ct on c.transmission_id = ct.id
where c.transmission_id is null;