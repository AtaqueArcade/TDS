package controlador;

import java.util.Date;
import java.util.HashMap;

import modelo.Usuario;
import persistencia.DAOusuario;
import persistencia.FactoriaDAO;

public class Controlador {

	private static Controlador instance;
	private Usuario currentuser; // TODO

	private FactoriaDAO dao;
	private DAOusuario userAdapter;
	private CatalogoUsuarios userCatalog;

	public static Controlador getInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null) {
			instance = new Controlador();
			instance.initialize();
		}
		return instance;
	}

	private void initialize() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		dao = FactoriaDAO.getInstance();
		userAdapter = dao.getDAOusuario();
		userCatalog = CatalogoUsuarios.getInstance();
	}

	public boolean register(String name, Date birthday, int phone, String username, String password) {
		if (!userCatalog.isUser(username)) {
			Usuario user = new Usuario(name, birthday, phone, username, password);
			userAdapter.registerUser(user);
			userCatalog.addUser(username, user);
			return true;
		}
		return false;
	}

	public boolean login(String username, String password) { // TODO
		if (userCatalog.match(username, password)) {
			currentuser = userCatalog.getUser(username);
			return true;
		}
		return false;
	}

	public String getCurrentUser() {
		return currentuser.getName();
	}

	public void logOut() {
		// TODO Auto-generated method stub
	}

	public HashMap<String, Usuario> getUserMap(String filter) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return CatalogoUsuarios.getInstance().getByFilter(filter);
	}
}
