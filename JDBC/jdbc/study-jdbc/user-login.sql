drop table if  exists t_user;
create table t_user(
    id int primary key auto_increment,
    login_name varchar(255) unique ,
    login_pwd varchar(255),
    real_name varchar(255)
);
insert into t_user( login_name, login_pwd, real_name) values('admin','root','管理员');
insert into t_user( login_name, login_pwd, real_name) values('zhangsan','123456','张三');
insert into t_user( login_name, login_pwd, real_name) values('lisi','741852','李四');
select * from t_user;