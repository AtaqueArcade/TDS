package modelo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Grupo extends Contacto {
	List<Contacto> components;

	public Grupo(String name) {
		super(name);
		components = new LinkedList<Contacto>();
		// TODO Auto-generated constructor stub
	}

	public Grupo(String name, int picture) {
		super(name, picture);
		components = new LinkedList<Contacto>();
		// TODO Auto-generated constructor stub
	}

	public Grupo(int id, String name, int picture) {
		super(id, name, picture);
		components = new LinkedList<Contacto>();
		// TODO Auto-generated constructor stub
	}

	public Grupo(int id, String name, int picture, List<Contacto> components) {
		super(id, name, picture);
		this.components = new LinkedList<Contacto>();
		components.stream().filter(c -> !this.components.contains(c)).forEach(this.components::add);
	}

	public Grupo(String name, List<Contacto> components) {
		super(name);
		this.components = new LinkedList<Contacto>();
		this.components.addAll(components);
	}

	public List<Contacto> getComponents() {
		return components;
	}
}
