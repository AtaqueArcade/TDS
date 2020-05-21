package persistencia;

import java.text.ParseException;
import java.util.List;

import modelo.Contacto;
import modelo.Grupo;
import modelo.Usuario;

public interface DAOusuario {
	public boolean registerUser(Usuario usuario);

	public void deleteUser(Usuario usuario);

	public Usuario getUser(int codigo) throws ParseException;

	public List<Usuario> getAllUsers();

	public void modifyUser(Usuario usuario);

	public boolean registerGroup(Grupo group);

	public void deleteGroup(Grupo gr);

	public List<Grupo> getAllGroups();

	public void modifyGroup(Grupo gr);

	public List<Contacto> getAsContacts(List<Integer> idList);

}