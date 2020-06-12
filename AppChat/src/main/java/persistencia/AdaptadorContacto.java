package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import beans.Entidad;
import beans.Propiedad;
import modelo.Contacto;
import modelo.ContactoIndividual;
import modelo.Grupo;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorContacto implements DAOcontacto {
	private static ServicioPersistencia server;
	private static AdaptadorContacto instance;

	public static AdaptadorContacto getInstance() {
		if (instance == null)
			return new AdaptadorContacto();
		else
			return instance;
	}

	private AdaptadorContacto() {
		server = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public boolean registerContact(Contacto contact) {
		Entidad eContact;
		try {
			if (contact != null)
				eContact = server.recuperarEntidad(contact.getId());
			return false;
		} catch (NullPointerException e) {
			eContact = new Entidad();
			eContact.setNombre("contact");
			if (contact instanceof ContactoIndividual) {
				eContact.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("msgId", Integer.toString(contact.getMsgId())),
						new Propiedad("userId", Integer.toString(contact.getUserId())),
						new Propiedad("name", contact.getName()), new Propiedad("picture", contact.getPicture()),
						new Propiedad("phone", contact.getPhone()))));
			} else { // instance of Grupo
				eContact.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("msgId", Integer.toString(contact.getMsgId())), new Propiedad("userId", ""),
						new Propiedad("name", contact.getName()), new Propiedad("picture", contact.getPicture()),
						new Propiedad("members", getMembersAsString(((Grupo) contact).getComponents())))));
			}
			eContact = server.registrarEntidad(eContact);
			contact.setId(eContact.getId());
			return true;
		}
	}

	@Override
	public boolean deleteContact(Contacto contact) {
		if (contact != null) {
			Entidad eContact = server.recuperarEntidad(contact.getId());
			return server.borrarEntidad(eContact);
		}
		return false;
	}

	@Override
	public Contacto getContact(int id) {
		Entidad eContact = null;
		boolean exists = true;
		Contacto result = null;
		try {
			eContact = server.recuperarEntidad(id);
		} catch (NullPointerException e) {
			exists = false;
		}
		if (exists) {
			int msgId = Integer.parseInt(server.recuperarPropiedadEntidad(eContact, "msgId"));
			int userId = 0;
			if (!server.recuperarPropiedadEntidad(eContact, "userId").equals(""))
				userId = Integer.parseInt(server.recuperarPropiedadEntidad(eContact, "userId"));
			String name = server.recuperarPropiedadEntidad(eContact, "name");
			String picture = server.recuperarPropiedadEntidad(eContact, "picture");
			if (userId != 0) { // single
				int phone = Integer.parseInt(server.recuperarPropiedadEntidad(eContact, "phone"));
				result = new ContactoIndividual(eContact.getId(), msgId, userId, name, picture, phone);
			} else { // group

				List<Contacto> members = getStringAsMembers(server.recuperarPropiedadEntidad(eContact, "members"));
				int admin = Integer.parseInt(server.recuperarPropiedadEntidad(eContact, "admin"));
				result = new Grupo(eContact.getId(), msgId, userId, name, picture, admin, members);
			}
		}
		return result;
	}

	@Override
	public void modifyContact(Contacto contact) {
		Entidad eContact = server.recuperarEntidad(contact.getId());
		if (contact instanceof ContactoIndividual) {
			for (Propiedad p : eContact.getPropiedades()) {
				if (p.getNombre().equals("nombre"))
					p.setValor(contact.getName());
				server.modificarPropiedad(p);
			}
		} else { // instance of Grupo
			for (Propiedad p : eContact.getPropiedades()) {
				if (p.getNombre().equals("nombre"))
					p.setValor(contact.getName());
				else if (p.getNombre().equals("picture"))
					p.setValor(contact.getPicture());
				else if (p.getNombre().equals("members")) {
					String contacts = getMembersAsString(((Grupo) contact).getComponents());
					p.setValor(contacts);
				}
				server.modificarPropiedad(p);
			}
		}
	}

	public void deleteAll() {
		ArrayList<Entidad> eContactList = server.recuperarEntidades("contact");
		for (Entidad eContact : eContactList) {
			server.borrarEntidad(eContact);
		}
	}

	// supporting methods
	private List<Contacto> getStringAsMembers(String idString) {
		List<Contacto> result = null;
		if (!idString.equals(""))
			result = Arrays.stream(idString.split(" ")).map(id -> getContact(Integer.valueOf(id)))
					.collect(Collectors.toList());
		return result;
	}

	private String getMembersAsString(List<Contacto> components) {
		String result = "";
		for (Contacto c : components) {
			result += c.getId() + " ";
		}
		return result.trim();
	}
}
