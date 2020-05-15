package modelo;

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
}
