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
		this.setId(this.getId() * -1);
	}

	public Grupo(int id, String name, int admin, String picture, List<Contacto> components) {
		super(id, name, picture);
		this.components = new LinkedList<Contacto>();
		this.admin = admin;
		components.stream().filter(c -> !this.components.contains(c)).forEach(this.components::add);
		this.setId(this.getId() * -1);
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
}