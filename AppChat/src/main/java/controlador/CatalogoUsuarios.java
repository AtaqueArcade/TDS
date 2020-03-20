package controlador;

import java.util.HashMap;

import modelo.Usuario;

public class CatalogoUsuarios {
	private HashMap<String, Usuario> catalog;
	private static CatalogoUsuarios instance;

	public static CatalogoUsuarios getInstance() {
		if (instance == null) {
			instance = new CatalogoUsuarios();
		}
		return instance;
	}

	private CatalogoUsuarios() {
		catalog = new HashMap<String, Usuario>();
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

	public boolean match(String username, String password) {
		Usuario user = catalog.get(username);
		if (user != null)
			if (user.getPassword().equals(password))
				return true;
		return false;
	}
}
