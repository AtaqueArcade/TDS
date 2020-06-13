package modelo;

import java.util.LinkedList;
import java.util.List;

public class Grupo extends Contacto {
	List<Contacto> components;
	int admin;

	public Grupo(int id, int msgId, int userId, String name, String picture, int admin, List<Contacto> components) {
		super(id, msgId, userId, name, picture);
		this.admin = admin;
		this.components = new LinkedList<Contacto>();
		if (components != null)
			this.components.addAll(components);
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