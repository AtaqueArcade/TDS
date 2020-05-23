package persistencia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import modelo.Mensaje;

public interface DAOmensajes {

	public HashMap<Integer, List<Mensaje>> getAllMessages();

	public int createMessageList();

	public LinkedList<Mensaje> getMessageList(int id);

	void modifyMessageList(int id, LinkedList<Mensaje> messageList);

	void deleteMessageList(int id);

	public void deleteAll();
}