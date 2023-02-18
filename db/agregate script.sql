create table devices(
    id serial primary key,
    dev_name varchar(255),
    price decimal
);

create table people(
    id serial primary key,
    people_name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(dev_name, price) values ('iphone', 1499.99);
insert into devices(dev_name, price) values ('gopro', 2500);
insert into devices(dev_name, price) values ('notepad', 6850.50);
insert into devices(dev_name, price) values ('quadrocopter', 9045.99);
insert into devices(dev_name, price) values ('ultrabook', 11909.44);

insert into people(people_name) values ('Bob');
insert into people(people_name) values ('Jack');
insert into people(people_name) values ('Kate');

insert into devices_people(device_id, people_id) values (1, 2);
insert into devices_people(device_id, people_id) values (2, 3);
insert into devices_people(device_id, people_id) values (4, 2);
insert into devices_people(device_id, people_id) values (3, 1);
insert into devices_people(device_id, people_id) values (2, 1);
insert into devices_people(device_id, people_id) values (5, 3);

select avg(price) av_price from devices;

select p.people_name, avg(d.price) av_price 
from devices d join devices_people dp on d.id = dp.device_id
join people p on p.id = dp.people_id
group by p.people_name;

select p.people_name, avg(d.price) av_price 
from devices d join devices_people dp on d.id = dp.device_id
join people p on p.id = dp.people_id
group by p.people_name
having avg(d.price) > 5000;