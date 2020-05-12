package persistencia;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import modelo.Contacto;
import modelo.Grupo;
import modelo.Usuario;

public interface DAOusuario {
	public boolean registerUser(Usuario usuario);

	public Usuario getUser(int codigo) throws ParseException;

	public List<Contacto> getAsContacts(List<Integer> idList);

	public List<Usuario> getAllUsers();

	public void deleteUser(Usuario usuario);

	public void modifyUser(Usuario usuario);

	public boolean registerGroup(Grupo group);

	public void modifyGroup(Grupo gr);

}