create table role(
	role_id serial primary key,
	role_name varchar(255) not NULL
);

create table users(
	user_id serial primary key,
	user_name varchar(255) not NULL,
	role_id int references role(role_id)
);

create table rules(
	rules_id serial primary key,
	rule_name varchar(255) not NULL
);

create table rules_role(
	rules_role_id serial primary key,
	role_id int references role(role_id),
	rules_id int references rules(rules_id)
);

create table category(
	category_id serial primary key,
	category_name varchar(255) not NULL
);

create table state(
	state_id serial primary key,
	status varchar(255) not NULL
);

create table item(
	item_id serial primary key,
	item_title char(50) not NULL,
	user_id int references users(user_id),
	category_id int references category(category_id),
	state_id int references state(state_id)
);

create table comments(
	comment_id serial primary key,
	comment_text text,
	item_id int references item(item_id)
);

create table attachs(
	attachs_id serial primary key,
	content text,
	item_id int references item(item_id)
);