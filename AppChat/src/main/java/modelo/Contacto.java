package modelo;

import java.util.Objects;

public abstract class Contacto {
	private int id;
	private String name;
	private String picture;

	public Contacto(String name) {
		id = Id.generateUniqueId();
		this.name = name;
	}

	public Contacto(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public abstract String getPhone();

	public abstract void setPhone(int phone);

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Contacto contact = (Contacto) o;
		return Objects.equals(id, contact.id) && Objects.equals(name, contact.name)
				&& Objects.equals(picture, contact.picture);
	}
}
