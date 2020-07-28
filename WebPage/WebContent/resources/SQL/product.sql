create table product (
	styleno varchar(20),
	c_name varchar(20),
	color varchar(20),
	unitPrice int,
	size varchar(20),
	description text,
	manufacturer varchar(20),
	category varchar(20),
	unitsInStock int,
	filename varchar(20),
	primary key(styleno)
) default CHARSET=utf8;

drop table product;

select * from product;

select * from product where styleno='M1001';