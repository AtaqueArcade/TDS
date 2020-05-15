package persistencia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import beans.Entidad;
import beans.Propiedad;
import modelo.Mensaje;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorMensajes implements DAOmensajes {
	private static ServicioPersistencia server;
	private static AdaptadorMensajes instance;

	public static AdaptadorMensajes getInstance() {
		if (instance == null)
			return new AdaptadorMensajes();
		else
			return instance;
	}

	private AdaptadorMensajes() {
		server = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public HashMap<Integer, List<Mensaje>> getAllMessages() {
		HashMap<Integer, List<Mensaje>> result = new HashMap<Integer, List<Mensaje>>();
		ArrayList<Entidad> eMessagesList = server.recuperarEntidades("messages");
		for (Entidad eMessage : eMessagesList)
			result.put(eMessage.getId(), getMessageList(eMessage.getId()));

		return result;
	}

	@Override
	public int createMessageList() {
		LinkedList<Mensaje> messages = new LinkedList<Mensaje>();
		Entidad eMessage = new Entidad();
		eMessage.setNombre("messages");
		eMessage.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(new Propiedad("contents", parseMsgToString(messages)))));
		eMessage = server.registrarEntidad(eMessage);
		return eMessage.getId();
	}

	@Override
	public LinkedList<Mensaje> getMessageList(int id) {
		Entidad eMessage;
		LinkedList<Mensaje> messages = new LinkedList<Mensaje>();
		try {
			eMessage = server.recuperarEntidad(id);
			String msgString = server.recuperarPropiedadEntidad(eMessage, "contents");
			messages = parseStringToMsg(msgString);
			return messages;
		} catch (NullPointerException e) {
			return null;
		}
	}

	@Override
	public void modifyMessageList(int id, LinkedList<Mensaje> messageList) {
		Entidad eMessage;
		eMessage = server.recuperarEntidad(id);
		for (Propiedad p : eMessage.getPropiedades()) {
			if (p.getNombre().equals("contents")) {
				p.setValor(parseMsgToString(messageList));
			}
			server.modificarPropiedad(p);
		}
	}

	@Override
	public void deleteMessageList(int id) {
		Entidad eMessage = server.recuperarEntidad(id);
		server.borrarEntidad(eMessage);
	}

	private LinkedList<Mensaje> parseStringToMsg(String msgString) {
		LinkedList<Mensaje> result = new LinkedList<Mensaje>();
		if (!msgString.equals("")) {
			List<String> messages = Arrays.stream(msgString.split(";")).map(String::intern)
					.collect(Collectors.toList());
			for (String mStr : messages) {
				Mensaje msg = null;
				String msgArr[] = mStr.split(",");
				String text = null;
				if (msgArr[0] != "")
					text = msgArr[0];
				msg = new Mensaje(text, Integer.parseInt(msgArr[1]), msgArr[2]);
				msg.setTime(LocalDateTime.parse(msgArr[3]));

				result.add(msg);
			}
		}
		return result;
	}

	private String parseMsgToString(LinkedList<Mensaje> messages) {
		String result = "";
		for (Mensaje m : messages) {
			if (m.getText() == null)
				result += ",";
			else
				result += (m.getText() + ",");
			result += (Integer.toString(m.getEmoticon()) + ",");
			result += (m.getSpeaker() + ",");
			result += (m.getTime().toString() + ";");
		}
		result = Optional.ofNullable(result).filter(sStr -> sStr.length() != 0)
				.map(sStr -> sStr.substring(0, sStr.length() - 1)).orElse(result);
		return result;
	}

	public void deleteAll() {
		ArrayList<Entidad> eMessageList = server.recuperarEntidades("message");
		for (Entidad eMessage : eMessageList) {
			server.borrarEntidad(eMessage);
		}
	}
}
