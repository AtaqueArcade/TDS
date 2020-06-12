package modelo;

public class ContactoIndividual extends Contacto {
	private int phone;

	public ContactoIndividual(int id, int msgId, int userId, String name, String picture, int phone) {
		super(id, msgId, userId, name, picture);
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
