package modelo;

import java.util.HashMap;

import persistencia.DAOusuario;
import persistencia.FactoriaDAO;

public class CatalogoUsuarios {
	private HashMap<String, Usuario> catalogo;
	private FactoriaDAO dao;
	private DAOusuario userDAO;

	public CatalogoUsuarios() {
		dao = FactoriaDAO.getInstance();
		userDAO = dao.getDAOusuario();
		for (Usuario user : userDAO.getAllUsers())
			catalogo.put(user.getUsername(), user);
	}
}
