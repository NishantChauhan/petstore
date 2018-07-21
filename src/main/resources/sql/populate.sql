SET FOREIGN_KEY_CHECKS=0;
truncate table photo_urls;
truncate table pet;
truncate table category;
truncate table mtm_pet_tag;
truncate table tag;
SET FOREIGN_KEY_CHECKS=1;

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
insert into photo_urls values ('/photoURL/Tom/Tom.jpg',1);
insert into	mtm_pet_tag values (1,1);
insert into	mtm_pet_tag values (2,1);
insert into	mtm_pet_tag values (3,1);

-- Jerry
insert into pet values (2,'Jerry','AVAILABLE',2);
insert into photo_urls values ('/photoURL/Jerry/Jerry.jpg',2);
insert into	mtm_pet_tag values (1,2);
insert into	mtm_pet_tag values (4,2);

-- Spike
insert into pet values (3,'Spike','AVAILABLE',3);
insert into photo_urls values ('/photoURL/Spike/Spike.jpg',3);
insert into	mtm_pet_tag values (1,3);

commit;