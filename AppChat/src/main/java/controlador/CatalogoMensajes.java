package controlador;

import java.util.HashMap;
import java.util.List;

import modelo.Mensaje;
import persistencia.DAOmensajes;
import persistencia.FactoriaDAO;

public class CatalogoMensajes {
	private FactoriaDAO dao;
	private DAOmensajes adaptadorMensajes;
	private HashMap<Integer, List<Mensaje>> catalog;
	private static CatalogoMensajes instance;

	public static CatalogoMensajes getInstance()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null) {
			instance = new CatalogoMensajes();
		}
		return instance;
	}

	private CatalogoMensajes() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		dao = FactoriaDAO.getInstance("persistencia.FactoriaDAOImp");
		adaptadorMensajes = dao.getDAOmensajes();
		this.catalog = new HashMap<Integer, List<Mensaje>>();
		catalog = adaptadorMensajes.getAllMessages();
	}

	public boolean newMessages(int id, List<Mensaje> messages) {
		return (catalog.put(id, messages) != null);
	}

	public boolean removeMessages(int id) {
		return (catalog.remove(id) != null);
	}

	public List<Mensaje> getMessages(int id) {
		return catalog.get(id);
	}
}
