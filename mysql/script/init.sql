create user 'login-demo'@'%' identified by '123456';
grant all privileges on test.* to 'login-demo'@'%';
use test;
create table nx_user(
id bigint primary key auto_increment,
name varchar(30) unique,
password varchar(50),
gender tinyint(2),
created_time datetime,
update_time datetime
);
