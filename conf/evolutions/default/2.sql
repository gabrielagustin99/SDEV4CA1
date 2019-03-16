# --- Sample dataset
 
# --- !Ups
 
delete from projects;
 
insert into department (id,name) values ( 1,'SDEV' );

 
insert into projects (id,name,description) values ( 1,'John Doe','Project' );

insert into DEPARTMENT_PROJECTS(department_id,projects_id) values (1,1);
