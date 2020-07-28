create table ordertable (
	order_no varchar(50),
    user_id varchar(20),
    styleno varchar(20),
    c_name varchar(20),
    quantity int,
    sdate date,
    primary key(order_no),
    # 외래키 product 테이블의 상품번호
    constraint o_prd1_fk foreign key(styleno) references product(styleno),
    # 외래키 member 테이블의 유저아이디
    constraint o_mem_fk foreign key(user_id) references member(user_id)
);

desc ordertable;

select * from ordertable;

drop table ordertable;