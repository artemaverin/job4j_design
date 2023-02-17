create table store (
	store_id serial primary key,
	store_name char(50) not NULL
);

create table city(
	city_id serial primary key,
	city_name char(50) not NULL
);

create table store_address(
	store_add_id serial primary key,
	store_id int references store(store_id),
	city_id int references city(city_id),
	address text not NULL
)

