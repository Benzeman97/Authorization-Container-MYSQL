
create user 'securitydb'@'%' identified by '14292';

grant all privileges on *.* to 'securitydb'@'%';

flush privileges;

create database securitydb;

use securitydb;

create table if not exists oauth_client_details(client_id varchar(120), client_secret varchar(300),web_server_redirect_uri varchar(300),
scope varchar(100), access_token_validity bigint, refresh_token_validity bigint, resource_ids varchar(120), authorized_grant_types varchar(300), authorities varchar(200),
additional_information varchar(500), autoapprove varchar(100), constraint pk_client_id primary key(client_id) );

create table if not exists permission(id int, name varchar(120),constraint pk_perm_id primary key(id));

create table if not exists role(id int,name varchar(120),constraint pk_role_id primary key(id));

create table if not exists role_permission(role_id int,permission_id int, constraint fk_role_id foreign key(role_id) references role(id), 
 constraint fk_perm_id foreign key(permission_id) references permission(id) );
 
 create table if not exists user_status(status_id int AUTO_INCREMENT, active int, acc_non_expired int,credential_non_expired int,acc_non_locked int, constraint pk_statusId primary key(status_id));
 
 alter table user_status AUTO_INCREMENT = 6001;
 
 create table if not exists voyage_user(email varchar(120), fname varchar(120), lname varchar(120), country varchar(100), dob date,password varchar(500),status_id int, 
 constraint pk_email primary key(email),constraint fk_status_id foreign key(status_id) references user_status(status_id));
 
 create table if not exists user_role(user_id varchar(120),role_id int, constraint fk_user_id foreign key(user_id) references voyage_user(email),
 constraint fk_role_id_user foreign key(role_id) references role(id));