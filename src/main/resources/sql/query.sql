
SELECT * FROM petstoredb.pet;
SELECT * FROM petstoredb.category;
SELECT * FROM petstoredb.tag;
SELECT * FROM petstoredb.photo_urls;
SELECT * FROM petstoredb.user;
-- Queries for Pet Store
-- Query all pets
SELECT 
    pet.name,
    pet.status,
    category.category_name,
    photo_urls.url,
    tag.tag_name,
    pet.pet_id,
    category.category_id,
    tag.tag_id
FROM
    pet,
    category,
    tag,
    photo_urls,
    mtm_pet_tag
WHERE
    pet.category_id_fk = category.category_id
        AND tag.tag_id = mtm_pet_tag.tag_id_fk
        AND pet.pet_id = mtm_pet_tag.pet_id_fk
        AND photo_urls.pet_id_fk = pet.pet_id
 ORDER BY pet_id
;

-- Replace photoURL
update photo_urls set url = replace(url,'ph','../../assets/ph') where pet_id_fk in (1,2,3) ;

-- query pet 
SELECT 
    pet.name,
    pet.status,
    category.category_name,
    photo_urls.url,
    tag.tag_name,
    pet.pet_id,
    category.category_id,
    tag.tag_id
FROM
    pet,
    category,
    tag,
    photo_urls,
    mtm_pet_tag
WHERE
    pet.category_id_fk = category.category_id
        AND tag.tag_id = mtm_pet_tag.tag_id_fk
        AND pet.pet_id = mtm_pet_tag.pet_id_fk
        AND photo_urls.pet_id_fk = pet.pet_id
        and pet_id = 4
ORDER BY pet_id
;

