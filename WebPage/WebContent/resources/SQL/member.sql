create table member(
	user_id varchar(20),
	user_pw varchar(20),
	user_name varchar(20),
	user_gender varchar(20),
	user_birth  varchar(20),
	user_email varchar(40),
	user_phone varchar(20),
	user_address varchar(60),
	resist_day varchar(50),
	primary key(user_id)
) default CHARSET=utf8;

desc member;

select * from member;

drop table member;
			