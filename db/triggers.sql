create table products (
    id serial primary key,
    p_name varchar(50),
    producer varchar(50),
    p_count integer default 0,
    price integer
);

create table history_of_price (
    id serial primary key,
    h_name varchar(50),
    price integer,
    h_date timestamp
);

--after
create or replace function tax()
	returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
	after insert
	on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

--before
create or replace function charge()
    returns trigger as
$$
    BEGIN
        new.price = new.price + new.price * 0.2
        return new;
    END;
$$
LANGUAGE 'plpgsql';
	
create trigger tax_before_trigger
	before insert 
	on products
    for each row
    execute procedure charge();

--history
create or replace function insertion()
	returns trigger as
$$
	BEGIN
		insert into history_of_price(name, price, date)
       	values(new.h_name, new.price, h_date);
       	return new;
	END
$$
LANGUAGE 'plpgsql';

create trigger insert_trigger
	after insert on history_of_price
	for each row
	execute procedure insertion();