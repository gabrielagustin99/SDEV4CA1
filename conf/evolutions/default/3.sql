# --- !Ups

delete from user;

insert into user (type,email,department_id,role,name,password) values ( 'a','admin@ofm.com',1,'admin','Alice Admin', 'password');
insert into user (type,email,department_id,role,name,password) values ( 'c','x00140008',1,'user','Alice Admin', 'password');

