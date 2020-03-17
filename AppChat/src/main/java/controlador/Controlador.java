package controlador;

import java.util.Date;

import modelo.Usuario;
import persistencia.DAOusuario;
import persistencia.FactoriaDAO;

public class Controlador {

	private static Controlador instance;
	private Usuario currentuser; // TODO

	private FactoriaDAO dao;
	private DAOusuario adaptadorUsuario;
	private CatalogoUsuarios catalogoUsuarios;

	public static Controlador getInstance() {
		if (instance == null) {
			instance = new Controlador();
			instance.initialize();
		}
		return instance;
	}

	private void initialize() {
		dao = FactoriaDAO.getInstance();
		adaptadorUsuario = dao.getDAOusuario();
		catalogoUsuarios = CatalogoUsuarios.getInstance();
	}

	public boolean register(String name, Date birthday, int phone, String username, String password) {
		if (!catalogoUsuarios.isUser(username)) {
			Usuario user = new Usuario(name, birthday, phone, username, password);
			adaptadorUsuario.registerUser(user);
			catalogoUsuarios.addUser(username, user);
			currentuser = user;
			return true;
		}
		return false;
	}

	public boolean login(String nombre, String password) { //TODO
		return true;
	}
}
