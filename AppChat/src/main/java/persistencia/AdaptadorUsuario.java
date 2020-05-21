package persistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import beans.Entidad;
import beans.Propiedad;
import modelo.Contacto;
import modelo.ContactoIndividual;
import modelo.Grupo;
import modelo.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuario implements DAOusuario {
	private static ServicioPersistencia server;
	private static AdaptadorUsuario instance;

	public static AdaptadorUsuario getInstance() {
		if (instance == null)
			return new AdaptadorUsuario();
		else
			return instance;
	}

	private AdaptadorUsuario() {
		server = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public boolean registerUser(Usuario user) {
		Entidad eUser;
		try {
			eUser = server.recuperarEntidad(user.getId());
			return false;
		} catch (NullPointerException e) {
			eUser = new Entidad();
			eUser.setNombre("user");
			eUser.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad("name", user.getName()),
					new Propiedad("birthday", user.getBirthday().toString()),
					new Propiedad("phone", Integer.toString(user.getPhone())),
					new Propiedad("username", user.getUsername()), new Propiedad("password", user.getPassword()),
					new Propiedad("picture", user.getPicture()),
					new Propiedad("premium", String.valueOf(user.isPremium())),
					new Propiedad("contacts", getAllContactIds(user.getContacts())),
					new Propiedad("messages", getAllMessageIds(user.getAllMessages())),
					new Propiedad("quote", user.getQuote()))));
			eUser = server.registrarEntidad(eUser);
			user.setId(eUser.getId());
			return true;
		}
	}

	@Override
	public void deleteUser(Usuario usuario) {
		Entidad eUser = server.recuperarEntidad(usuario.getId());
		server.borrarEntidad(eUser);
	}

	@Override
	public Usuario getUser(int id) {
		Entidad eUser = null;
		boolean exists = true;
		try {
			eUser = server.recuperarEntidad(id);
		} catch (NullPointerException e) {
			exists = false;
		}
		if (exists) {
			String name = server.recuperarPropiedadEntidad(eUser, "name");
			SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.UK);
			Date birthday = null;
			try {
				birthday = parser.parse(server.recuperarPropiedadEntidad(eUser, "birthday"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			int phone = Integer.parseInt(server.recuperarPropiedadEntidad(eUser, "phone"));
			String username = server.recuperarPropiedadEntidad(eUser, "username");
			String password = server.recuperarPropiedadEntidad(eUser, "password");
			String picture = server.recuperarPropiedadEntidad(eUser, "picture");
			boolean premium = Boolean.parseBoolean(server.recuperarPropiedadEntidad(eUser, "premium"));
			String quote = server.recuperarPropiedadEntidad(eUser, "quote");
			String idListString = server.recuperarPropiedadEntidad(eUser, "contacts");
			List<Integer> idList = new ArrayList<Integer>();
			if (!idListString.equals(""))
				idList = Arrays.stream(idListString.split(" ")).map(Integer::valueOf).collect(Collectors.toList());
			List<Contacto> contacts = getAsContacts(idList);
			idListString = server.recuperarPropiedadEntidad(eUser, "messages");
			if (!idListString.equals(""))
				idList = Arrays.stream(idListString.split(" ")).map(Integer::valueOf).collect(Collectors.toList());
			Map<Contacto, Integer> result = IntStream.range(0, contacts.size()).boxed()
					.collect(Collectors.toMap(contacts::get, idList::get));
			Usuario user = new Usuario(id, name, birthday, phone, username, password, picture, premium, result, quote);
			return user;
		}
		return null;
	}

	@Override
	public ArrayList<Usuario> getAllUsers() {
		ArrayList<Usuario> userList = new ArrayList<Usuario>();
		ArrayList<Entidad> eUserList = server.recuperarEntidades("user");
		for (Entidad eUser : eUserList) {
			userList.add(getUser(eUser.getId()));
		}
		return userList;
	}

	@Override
	public void modifyUser(Usuario user) {
		Entidad eUser;
		eUser = server.recuperarEntidad(user.getId());
		for (Propiedad p : eUser.getPropiedades()) {
			if (p.getNombre().equals("picture")) {
				p.setValor(user.getPicture());
			} else if (p.getNombre().equals("quote")) {
				p.setValor(user.getQuote());
			} else if (p.getNombre().equals("contacts")) {
				String contacts = getAllContactIds(user.getContacts());
				p.setValor(contacts);
			} else if (p.getNombre().equals("premium")) {
				String premium = Boolean.toString(user.getPremium());
				p.setValor(premium);
			} else if (p.getNombre().equals("messages")) {
				String messages = getAllMessageIds(user.getAllMessages());
				p.setValor(messages);
			}
			server.modificarPropiedad(p);
		}
	}

	@Override
	public boolean registerGroup(Grupo group) {
		Entidad eGroup;
		try {
			eGroup = server.recuperarEntidad(group.getId());
			return false;
		} catch (NullPointerException e) {
			eGroup = new Entidad();
			eGroup.setNombre("group");
			eGroup.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad("name", group.getName()),
					new Propiedad("admin", Integer.toString(group.getAdmin())),
					new Propiedad("picture", group.getPicture()),
					new Propiedad("members", getAllContactIds(group.getComponents())))));
			eGroup = server.registrarEntidad(eGroup);
			group.setId(eGroup.getId());
			return true;
		}
	}

	@Override
	public void deleteGroup(Grupo group) {
		Entidad eGroup = server.recuperarEntidad(group.getId());
		server.borrarEntidad(eGroup);
	}

	@Override
	public List<Grupo> getAllGroups() {
		ArrayList<Grupo> groupList = new ArrayList<Grupo>();
		ArrayList<Entidad> eGroupList = server.recuperarEntidades("group");
		List<Contacto> lg = getAsContacts(eGroupList.stream().map(Entidad::getId).collect(Collectors.toList()));
		groupList.addAll(lg.stream().map(contacto -> (Grupo) contacto).collect(Collectors.toList()));
		return groupList;
	}

	@Override
	public void modifyGroup(Grupo group) {
		Entidad eGroup;
		eGroup = server.recuperarEntidad(group.getId());
		for (Propiedad p : eGroup.getPropiedades()) {
			if (p.getNombre().equals("picture")) {
				p.setValor(group.getPicture());
			} else if (p.getNombre().equals("members")) {
				String contacts = getAllContactIds(group.getComponents());
				p.setValor(contacts);
			}
			server.modificarPropiedad(p);
		}
	}

	// Includes both users and groups
	@Override
	public ArrayList<Contacto> getAsContacts(List<Integer> idList) {
		ArrayList<Contacto> result = new ArrayList<Contacto>();
		if (idList != null) {
			for (int id : idList) {
				if (server.recuperarEntidad(id).getNombre().equals("user")) {
					Entidad eUser = server.recuperarEntidad(id);
					String contactName = server.recuperarPropiedadEntidad(eUser, "name");
					String contactPicture = server.recuperarPropiedadEntidad(eUser, "picture");
					int contactPhone = Integer.parseInt(server.recuperarPropiedadEntidad(eUser, "phone"));
					Contacto contact = new ContactoIndividual(id, contactName, contactPicture, contactPhone);
					result.add(contact);
				} else if (server.recuperarEntidad(id).getNombre().equals("group")) {
					Entidad eGroup = server.recuperarEntidad(id);
					String groupName = server.recuperarPropiedadEntidad(eGroup, "name");
					int admin = Integer.parseInt(server.recuperarPropiedadEntidad(eGroup, "admin"));
					String contactPicture = server.recuperarPropiedadEntidad(eGroup, "picture");
					String idListString = server.recuperarPropiedadEntidad(eGroup, "members");
					List<Integer> memberList = new ArrayList<Integer>();
					if (!idListString.equals(""))
						memberList = Arrays.stream(idListString.split(" ")).map(Integer::valueOf)
								.collect(Collectors.toList());
					List<Contacto> contacts = getAsContacts(memberList);
					Grupo group = new Grupo(id, groupName, admin, contactPicture, contacts);
					result.add(group);
				}
			}
			return result;
		}
		return null;
	}

	// Supporting methods
	private String getAllContactIds(List<Contacto> list) {
		String result = "";
		for (Contacto c : list) {
			result += c.getId() + " ";
		}
		return result.trim();
	}

	private String getAllMessageIds(List<Integer> list) {
		String result = "";
		for (int id : list) {
			result += id + " ";
		}
		return result.trim();
	}

	public void deleteAll() {
		ArrayList<Entidad> eUserList = server.recuperarEntidades("user");
		for (Entidad eUser : eUserList) {
			server.borrarEntidad(eUser);
		}
		ArrayList<Entidad> eGroupList = server.recuperarEntidades("group");
		for (Entidad eGroup : eGroupList) {
			server.borrarEntidad(eGroup);
		}
	}
}
