# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table department (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  constraint pk_department primary key (id)
);

create table department_projects (
  department_id                 bigint not null,
  projects_id                   bigint not null,
  constraint pk_department_projects primary key (department_id,projects_id)
);

create table projects (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  description                   varchar(255),
  constraint pk_projects primary key (id)
);

create table user (
  type                          varchar(31) not null,
  email                         varchar(255) not null,
  department_id                 bigint not null,
  name                          varchar(255),
  password                      varchar(255),
  role                          varchar(255),
  street1                       varchar(255),
  street2                       varchar(255),
  town                          varchar(255),
  post_code                     varchar(255),
  credit_card                   varchar(255),
  constraint pk_user primary key (email)
);

alter table department_projects add constraint fk_department_projects_department foreign key (department_id) references department (id) on delete restrict on update restrict;
create index ix_department_projects_department on department_projects (department_id);

alter table department_projects add constraint fk_department_projects_projects foreign key (projects_id) references projects (id) on delete restrict on update restrict;
create index ix_department_projects_projects on department_projects (projects_id);

alter table user add constraint fk_user_department_id foreign key (department_id) references department (id) on delete restrict on update restrict;
create index ix_user_department_id on user (department_id);


# --- !Downs

alter table department_projects drop constraint if exists fk_department_projects_department;
drop index if exists ix_department_projects_department;

alter table department_projects drop constraint if exists fk_department_projects_projects;
drop index if exists ix_department_projects_projects;

alter table user drop constraint if exists fk_user_department_id;
drop index if exists ix_user_department_id;

drop table if exists department;

drop table if exists department_projects;

drop table if exists projects;

drop table if exists user;

