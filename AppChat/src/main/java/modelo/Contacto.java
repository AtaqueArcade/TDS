package modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class Contacto {
	// The contact's id
	private int id;
	// Message list associated
	private int msgId;
	// The represented user's id
	private int userId;

	private String name;
	private String picture;
	private List<Mensaje> mensajes;

	public Contacto(String name, int userId) {
		id = Id.generateUniqueId();
		msgId = Id.generateUniqueId();
		this.name = name;
		this.userId = userId;
		this.mensajes = new LinkedList<Mensaje>();
	}

	public Contacto(int id, int msgId, int userId, String name, String picture) {
		this.id = id;
		this.msgId = msgId;
		this.userId = userId;
		this.name = name;
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public List<Mensaje> getMensajes() {
		return mensajes;
	}

	public boolean addMensaje(Mensaje m) {
		return mensajes.add(m);
	}

	public void removeMensajes() {
		mensajes = new LinkedList<Mensaje>();
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
		return Objects.equals(id, contact.id) && Objects.equals(msgId, contact.msgId)
				&& Objects.equals(name, contact.name) && Objects.equals(picture, contact.picture)
				&& Objects.equals(mensajes, contact.mensajes);
	}
}
