package modelo;

import java.util.LinkedList;

public abstract class Contacto {
	private int id;
	private String name;
	private String picture;
	private LinkedList<Mensaje> mensajes;

	public Contacto(String name) {
		id = Id.generateUniqueId();
		this.name = name;
		this.picture = null;
		this.mensajes = new LinkedList<Mensaje>();
	}

	public Contacto(String name, String picture) {
		this(name);
		this.picture = picture;
		this.mensajes = new LinkedList<Mensaje>();
	}

	public Contacto(int id, String name, String picture) {
		this(name, picture);
		this.id = id;
		this.mensajes = new LinkedList<Mensaje>();
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

	public LinkedList<Mensaje> getMensajes() {
		return mensajes;
	}

	public void addMensaje(Mensaje m) {
		mensajes.add(m);
	}

	public void resetMensajes() {
		mensajes = new LinkedList<Mensaje>();
	}
}
