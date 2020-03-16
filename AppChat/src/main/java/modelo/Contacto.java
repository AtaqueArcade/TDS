package modelo;

public abstract class Contacto {
	private int id;
	private String name;
	private int picture;

	public Contacto(String name) {
		id = Id.generateUniqueId();
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

	public int getPicture() {
		return picture;
	}

	public void setPicture(int picture) {
		this.picture = picture;
	}
}
