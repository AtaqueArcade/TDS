package modelo;

import java.util.LinkedList;
import java.util.List;

public class Grupo extends Contacto {
	List<Contacto> components;
	int admin;

	public Grupo(String name, int admin, List<Contacto> components) {
		super(name);
		this.admin = admin;
		this.components = components;
	}

	public Grupo(int id, String name, int admin, String picture, List<Contacto> components) {
		super(id, name);
		this.components = new LinkedList<Contacto>();
		this.admin = admin;
		components.stream().filter(c -> !this.components.contains(c)).forEach(this.components::add);
		this.setPicture(picture);
	}

	public List<Contacto> getComponents() {
		return components;
	}

	public void setComponents(List<Contacto> components) {
		this.components = components;
	}

	public int getAdmin() {
		return admin;
	}

	@Override
	public String getPhone() {
		String phones = "";
		for (Contacto c : components) {
			phones += c.getPhone();
			phones += "\n";
		}
		return phones;
	}

	@Override
	public void setPhone(int phone) {
		// unused
	}
}