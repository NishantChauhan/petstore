SET FOREIGN_KEY_CHECKS=0;
truncate table photo_urls;
truncate table pet;
truncate table category;
truncate table mtm_pet_tag;
truncate table tag;

-- security
truncate table user;
truncate table OAUTH_CLIENT_DETAILS;
truncate table oauth_access_token;
SET FOREIGN_KEY_CHECKS=1;

-- user
insert into user values (1,'u','$2a$04$f/Ki8C7BQQ2yrF.jaRjtZe0DH2ib0Z9LQYEAma/QHB8B1M4gqBmE2','USER');
insert into user values (2,'a','$2a$04$f/Ki8C7BQQ2yrF.jaRjtZe0DH2ib0Z9LQYEAma/QHB8B1M4gqBmE2','ADMIN');

-- oauth
INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
 VALUES ('petapp', 'pet_resource_id','$2a$04$IYJ9wmOSt9GrQ9zx0RoPsu.rWZ/GoMkfBjHgZ/Q6bgttAxwaKlfeO','read,write', 'password,authorization_code,refresh_token,implicit', 'ADMIN,USER', 120, 300);



-- Categories
insert into category values (1,'pussy-cat');
insert into category values (2,'house-mouse');
insert into category values (3,'bulldog');
insert into category values (10,'demo-category');




-- Tags
insert into tag values (1,'cartoon');
insert into tag values (2,'loves-milk');
insert into tag values (3,'eats-mice');
insert into tag values (4,'loves-cheese');
insert into tag values (10,'demo-tag');

-- Tom
insert into pet values (1,'Tom','AVAILABLE',1);
insert into photo_urls values ('photoURL/1/Tom.png',1);
insert into	mtm_pet_tag values (1,1);
insert into	mtm_pet_tag values (2,1);
insert into	mtm_pet_tag values (3,1);

-- Jerry
insert into pet values (2,'Jerry','AVAILABLE',2);
insert into photo_urls values ('photoURL/2/Jerry.png',2);
insert into	mtm_pet_tag values (1,2);
insert into	mtm_pet_tag values (4,2);

-- Spike
insert into pet values (3,'Spike','AVAILABLE',3);
insert into photo_urls values ('photoURL/3/Spike.png',3);
insert into	mtm_pet_tag values (1,3);

commit;