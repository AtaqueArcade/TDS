package controlador;

import java.util.Date;

import modelo.Usuario;
import persistencia.DAOusuario;
import persistencia.FactoriaDAO;

public class Controlador {

	private static Controlador instance;
	private Usuario currentuser; // TODO

	private FactoriaDAO dao;
	private DAOusuario userAdapter;
	private CatalogoUsuarios userCatalog;

	public static Controlador getInstance() {
		if (instance == null) {
			instance = new Controlador();
			instance.initialize();
		}
		return instance;
	}

	private void initialize() {
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
}
