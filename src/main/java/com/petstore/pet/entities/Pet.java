package com.petstore.pet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Pet
 */

@Entity
@Table(name = "pet", schema = "petstoredb", uniqueConstraints= {@UniqueConstraint(columnNames="pet_id")})
@NamedQueries(value= { @NamedQuery(name="findPetById",query="select p from Pet p where p.id=:pet_id")})
public class Pet {

	@Id
	@Column(name="pet_id", nullable=false)
	private Long id = null;

	@Column(name="name", nullable=false)
	private String name = null;

	@OneToOne
	@JoinColumn(name="category_id_fk")
	private Category category = null;
/*
	@OneToMany(mappedBy="pet_id_fk", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<String> photoUrls = new ArrayList<String>();

	@OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="mtm_pet_id",
            joinColumns=
                @JoinColumn(name="tag_id_fk"),
            inverseJoinColumns=
                @JoinColumn(name="pet_id_fk")
        )
	private List<Tag> tags = null;

	*//**
	 * pet status in the store
	 *//*
	public enum StatusEnum {
		AVAILABLE("available"),

		PENDING("pending"),

		SOLD("sold");

		private String value;

		StatusEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static StatusEnum fromValue(String text) {
			for (StatusEnum b : StatusEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	private StatusEnum status = null;

	public Pet id(Long id) {
		this.id = id;
		return this;
	}
*/
	/**
	 * Get id
	 * 
	 * @return id
	 **/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pet category(Category category) {
		this.category = category;
		return this;
	}

	/**
	 * Get category
	 * 
	 * @return category
	 **/
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Pet name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get name
	 * 
	 * @return name
	 **/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
/*
	public Pet photoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
		return this;
	}

	public Pet addPhotoUrlsItem(String photoUrlsItem) {
		this.photoUrls.add(photoUrlsItem);
		return this;
	}

	*//**
	 * Get photoUrls
	 * 
	 * @return photoUrls
	 **//*
	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public Pet tags(List<Tag> tags) {
		this.tags = tags;
		return this;
	}

	public Pet addTagsItem(Tag tagsItem) {
		if (this.tags == null) {
			this.tags = new ArrayList<Tag>();
		}
		this.tags.add(tagsItem);
		return this;
	}

	*//**
	 * Get tags
	 * 
	 * @return tags
	 **//*
	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Pet status(StatusEnum status) {
		this.status = status;
		return this;
	}

	*//**
	 * pet status in the store
	 * 
	 * @return status
	 **//*
	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Pet pet = (Pet) o;
		return Objects.equals(this.id, pet.id) && Objects.equals(this.category, pet.category)
				&& Objects.equals(this.name, pet.name) && Objects.equals(this.photoUrls, pet.photoUrls)
				&& Objects.equals(this.tags, pet.tags) && Objects.equals(this.status, pet.status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, category, name, photoUrls, tags, status);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Pet {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    category: ").append(toIndentedString(category)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    photoUrls: ").append(toIndentedString(photoUrls)).append("\n");
		sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	*//**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 *//*
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
*/
}
