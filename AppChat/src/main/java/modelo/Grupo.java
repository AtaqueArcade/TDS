package modelo;

import java.util.LinkedList;
import java.util.List;

public class Grupo extends Contacto {
	List<Contacto> components;
	Usuario admin;

	public Grupo(String name, Usuario admin) {
		super(name);
		components = new LinkedList<Contacto>();
		this.admin = admin;
	}

	public Grupo(String name, Usuario admin, int picture) {
		super(name, picture);
		components = new LinkedList<Contacto>();
		this.admin = admin;
	}

	public Grupo(int id, String name, Usuario admin, int picture) {
		super(id, name, picture);
		components = new LinkedList<Contacto>();
		this.admin = admin;
	}

	public Grupo(int id, String name, Usuario admin, int picture, List<Contacto> components) {
		super(id, name, picture);
		this.components = new LinkedList<Contacto>();
		this.admin = admin;
		components.stream().filter(c -> !this.components.contains(c)).forEach(this.components::add);
	}

	public Grupo(String name, Usuario admin, List<Contacto> components) {
		super(name);
		this.components = new LinkedList<Contacto>();
		this.admin = admin;
		this.components.addAll(components);
	}

	public List<Contacto> getComponents() {
		return components;
	}

	public void setComponents(List<Contacto> components) {
		this.components = components;
	}

	public Usuario getAdmin() {
		return admin;
	}
}