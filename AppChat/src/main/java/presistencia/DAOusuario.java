package presistencia;

import java.util.List;

import modelo.Usuario;

public interface DAOusuario {
	public interface DAOUsuario {
		public void registerUser (Usuario usuario);
		public void deleteUser(Usuario usuario);
		public void modifyUser(Usuario usuario);
		public Usuario getUser(int codigo);
		public List<Usuario> getAllUsers();
	}
}
