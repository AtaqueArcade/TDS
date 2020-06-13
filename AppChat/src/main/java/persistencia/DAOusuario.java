package persistencia;

import java.text.ParseException;
import java.util.List;
import modelo.Usuario;

public interface DAOusuario {
	public boolean registerUser(Usuario usuario);

	public void deleteUser(Usuario usuario);

	public Usuario getUser(int codigo) throws ParseException;

	public List<Usuario> getAllUsers();

	public void modifyUser(Usuario usuario);

	public void deleteAll();
}