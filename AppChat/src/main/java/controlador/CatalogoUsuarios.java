package controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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
		dao = FactoriaDAO.getInstance("persistencia.FactoriaDAOImp");
		adaptadorUsuario = dao.getDAOusuario();
		this.catalog = new HashMap<String, Usuario>();
		List<Usuario> userList = adaptadorUsuario.getAllUsers();
		for (Usuario user : userList) {
			catalog.put(user.getUsername(), user);
		}
	}

	public boolean addUser(String username, Usuario user) {
		return (catalog.put(username, user) != null);
	}

	public boolean removeUser(String username) {
		return (catalog.remove(username) != null);
	}

	public boolean isUser(String username) {
		return catalog.containsKey(username);
	}

	public Usuario getUser(String username) {
		return catalog.get(username);
	}

	public Usuario getUser(int id) {
		Entry<String, Usuario> entry = catalog.entrySet().stream().filter(e -> e.getValue().getId() == id).findFirst()
				.get();
		if (entry != null)
			return entry.getValue();
		return null;
	}

	public boolean match(String username, String password) {
		Usuario user = catalog.get(username);
		if (user != null && password != null)
			if (user.getPassword().equals(password))
				return true;
		return false;
	}

	public List<String> getByFilter(String filter) {
		List<String> result = catalog.keySet().stream().filter(username -> username.contains(filter))
				.collect(Collectors.toList());
		return result;
	}

	public void modifyUser(Usuario user) {
		Usuario modifiedUser = this.getUser(user.getId());
		modifiedUser = user;
		adaptadorUsuario.modifyUser(modifiedUser);
	}
}
