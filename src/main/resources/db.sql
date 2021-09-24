

create table user(
id int(32) primary key AUTO_INCREMENT,
name varchar(64) not null default '',
age int(16) not null default 0,
email varchar(32) not null default '',
phone varchar(32) not null default '',
description varchar(64) default '',
create_time timestamp default CURRENT_TIMESTAMP ,
update_time timestamp default CURRENT_TIMESTAMP ,
);