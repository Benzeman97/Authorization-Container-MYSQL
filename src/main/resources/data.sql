
insert into oauth_client_details (client_id,client_secret,web_server_redirect_uri,scope,access_token_validity,
refresh_token_validity,resource_ids,authorized_grant_types,authorities,additional_information,autoapprove) values ('voyage','{bcrypt}$2a$12$BySTtzNHEe0gQLETlyKvTOB9Hiwr63PM1VhtGmGnmDJRR5wwYvVlK',
'http://127.0.0.1:8090','create,read,update,delete',7200000,9000000,'user-info-service,movie-info-service,tvseries-info-service,payment-service',
'authorization_code,password,refresh_token,implicit,client_credentials','role_user,role_admin,role_moderator',null,null);


insert into permission (id,name) values (10,'CAN_CREATE');
insert into permission (id,name) values (20,'CAN_READ');
insert into permission (id,name) values (30,'CAN_UPDATE');
insert into permission (id,name) values (40,'CAN_DELETE');

insert into role (id,name) values (101,'ROLE_USER');
insert into role (id,name) values (102,'ROLE_MODERATOR');
insert into role (id,name) values (103,'ROLE_ADMIN');

insert into user_status(active,acc_non_expired,credential_non_expired,acc_non_locked)  values (1,1,1,1);
insert into user_status(active,acc_non_expired,credential_non_expired,acc_non_locked)  values (1,1,1,1);



insert into role_permission (role_id,permission_id) values (101,10);
insert into role_permission (role_id,permission_id) values (102,10),(102,20),(102,30);
insert into role_permission (role_id,permission_id) values (103,10),(103,20),(103,30),(103,40);

insert into voyage_user (email,fname,lname,country,dob,password,status_id) values ('benz@gmail.com','nafaz','benzema','sri lanka',STR_TO_DATE('02-MAY-1997','%d-%M-%Y'),
'{bcrypt}$2a$12$9d1bSx10ALXdu9Ild.DH2.x2IcJtiqwlTVFYAQMFzJD34ZU7Y6VIS',6001);

insert into voyage_user (email,fname,lname,country,dob,password,status_id) values ('brook@gmail.com','kelly','brook','united kingdom',STR_TO_DATE('19-Jul-1979','%d-%M-%Y'),
'{bcrypt}$2a$12$51tAz50NL1VlyJMTM1kqIO6dbJHgWz6I/McX4hMStb9Jr6FNa/8h6',6002);

insert into user_role (user_id,role_id) values ('benz@gmail.com',103);

insert into user_role (user_id,role_id) values ('brook@gmail.com',101);





