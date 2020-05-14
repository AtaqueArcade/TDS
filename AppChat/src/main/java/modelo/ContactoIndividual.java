package modelo;

public class ContactoIndividual extends Contacto {
	private int phone;

	public ContactoIndividual(int id, String name, String picture, int phone) {
		super(id, name);
		this.setPicture(picture);
		this.setPhone(phone);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getPhone() {
		return Integer.toString(phone);
	}

	@Override
	public void setPhone(int phone) {
		this.phone = phone;
	}
}
