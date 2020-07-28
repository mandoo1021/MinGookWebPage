create table ordertable (
	order_no varchar(50),
    user_id varchar(20),
    styleno varchar(20),
    c_name varchar(20),
    quantity int,
    sdate date,
    primary key(order_no),
    # �ܷ�Ű product ���̺��� ��ǰ��ȣ
    constraint o_prd1_fk foreign key(styleno) references product(styleno),
    # �ܷ�Ű member ���̺��� �������̵�
    constraint o_mem_fk foreign key(user_id) references member(user_id)
);

desc ordertable;

select * from ordertable;

drop table ordertable;