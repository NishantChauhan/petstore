SELECT pet.name,pet.status,category.category_name,photo_url.url,tag.tag_name,
pet.pet_id,
category.category_id,
tag.tag_id,
photo_url.photo_url_id

 FROM pet,category,tag,photo_url,mtm_pet_tag
where pet.category_id_fk=category.category_id
and tag.tag_id=mtm_pet_tag.tag_id_fk
and pet.pet_id=mtm_pet_tag.pet_id_fk
and photo_url.pet_id_fk=pet.pet_id
;