create table users (
	user_id serial primary key,
	user_name char(50) not NULL
);

create table mail (
	mail_id serial primary key,
	user_id int references users(user_id) unique,
	mail text not NULL
);

