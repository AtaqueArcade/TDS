package controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import TDS.AppChat.AppChat_Constants;
import modelo.Usuario;
import persistencia.DAOusuario;
import persistencia.FactoriaDAO;

public class CatalogoUsuarios {
	private FactoriaDAO dao;
	private DAOusuario adaptadorUsuario;
	private HashMap<String, Usuario> catalog;
	private static CatalogoUsuarios instance;

	public static CatalogoUsuarios getInstance()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null) {
			instance = new CatalogoUsuarios();
		}
		return instance;
	}

	private CatalogoUsuarios() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		dao = FactoriaDAO.getInstance(AppChat_Constants.DAO);
		adaptadorUsuario = dao.getDAOusuario();
		updateAllUsers();
	}

	public boolean addUser(Usuario user) {
		if (!adaptadorUsuario.registerUser(user))
			return false;
		return (catalog.put(user.getUsername(), user) == null);
	}

	public boolean removeUser(String username) {
		Usuario u = getUser(username);
		if (u != null)
			adaptadorUsuario.deleteUser(u);
		return (catalog.remove(username) != null);
	}

	public Usuario getUser(String username) {
		updateAllUsers();
		return catalog.get(username);
	}

	public Usuario getUser(int id) {
		updateAllUsers();
		Entry<String, Usuario> entry = catalog.entrySet().stream().filter(e -> e.getValue().getId() == id).findFirst()
				.get();
		if (entry != null)
			return entry.getValue();
		return null;
	}

	public void modifyUser(Usuario user) {
		Usuario modifiedUser = this.getUser(user.getId());
		modifiedUser = user;
		catalog.put(modifiedUser.getUsername(), modifiedUser);
		adaptadorUsuario.modifyUser(modifiedUser);
	}

	public boolean isUser(String username) {
		updateAllUsers();
		return catalog.containsKey(username);
	}

	public boolean match(String username, String password) {
		updateAllUsers();
		Usuario user = catalog.get(username);
		if (user != null && password != null)
			if (user.getPassword().equals(password))
				return true;
		return false;
	}

	public List<String> getByFilter(String filter) {
		updateAllUsers();
		List<String> result = catalog.keySet().stream().filter(username -> username.contains(filter))
				.collect(Collectors.toList());
		return result;
	}

	private void updateAllUsers() {
		this.catalog = new HashMap<String, Usuario>();
		List<Usuario> userList = adaptadorUsuario.getAllUsers();
		for (Usuario user : userList) {
			catalog.put(user.getUsername(), user);
		}
	}
}