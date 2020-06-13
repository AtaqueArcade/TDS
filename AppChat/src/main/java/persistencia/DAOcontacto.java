package persistencia;

import modelo.Contacto;

public interface DAOcontacto {
	public boolean registerContact(Contacto contact);

	public boolean deleteContact(Contacto contact);

	public Contacto getContact(int codigo);

	public void modifyContact(Contacto contact);

	public void deleteAll();
}