create table adminContact (
	num int not null auto_increment,
    user_id varchar(10) not null,
    user_name varchar(10) not null,
    user_phone varchar(20) not null,
    subject varchar(100) not null,
    content text not null,
    regist_day varchar(30), 
    ip varchar(20), 
    primary key(num),
    // 외래키 member 테이블의 유저아이디
    constraint ad_mem_fk foreign key(user_id) references member(user_id)
);

drop table adminContact;

desc adminContact;