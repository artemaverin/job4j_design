create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

--procedure
create or replace procedure delete_data(d_id integer)
language 'plpgsql'
as
$$
	begin
		delete from products where id = d_id;
	end;
$$;

--function
create or replace function del_func(d_id integer)
returns text
language 'plpgsql'
as
$$
	declare res_id text;
	begin
		select into res_id concat('id deleted = ', id) from products where id = d_id;
		delete from products where id = d_id;		
	return res_id;
	end;
$$;

