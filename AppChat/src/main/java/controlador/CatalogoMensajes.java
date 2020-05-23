package controlador;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import modelo.AppChat_Constants;
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
		dao = FactoriaDAO.getInstance(AppChat_Constants.DAO);
		adaptadorMensajes = dao.getDAOmensajes();
		this.catalog = new HashMap<Integer, List<Mensaje>>();
		catalog = adaptadorMensajes.getAllMessages();
	}

	public int createMessage() {
		int msgId = adaptadorMensajes.createMessageList();
		updateAllMessages();
		return msgId;
	}

	public boolean addMessages(int id, LinkedList<Mensaje> messages) {
		updateAllMessages();
		boolean success = false;
		if (catalog.put(id, messages) != null) {
			success = true;
			adaptadorMensajes.modifyMessageList(id, messages);
		}
		return success;
	}

	public boolean addMessage(int id, Mensaje message) {
		updateAllMessages();
		boolean success = false;
		if (catalog.get(id).add(message)) {
			success = true;
			adaptadorMensajes.modifyMessageList(id, (LinkedList<Mensaje>) catalog.get(id));
		}
		return success;
	}

	public boolean removeMessages(int id) {
		updateAllMessages();
		adaptadorMensajes.deleteMessageList(id);
		return (catalog.remove(id) != null);
	}

	public List<Mensaje> getMessages(int id) {
		updateAllMessages();
		return catalog.get(id);
	}

	private void updateAllMessages() {
		catalog = adaptadorMensajes.getAllMessages();
	}
}
