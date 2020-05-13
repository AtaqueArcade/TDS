package persistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
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
	private SimpleDateFormat dateFormat;
	private static AdaptadorUsuario instance;

	public static AdaptadorUsuario getInstance() {
		if (instance == null)
			return new AdaptadorUsuario();
		else
			return instance;
	}

	private AdaptadorUsuario() {
		server = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}

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
					new Propiedad("contacts", getAllIds(user.getContacts())),
					new Propiedad("quote", user.getQuote()))));
			eUser = server.registrarEntidad(eUser);
			user.setId(eUser.getId());
			return true;
		}
	}

	public boolean registerGroup(Grupo group) {
		Entidad eGroup;
		boolean exists = true;
		try {
			eGroup = server.recuperarEntidad(group.getId());
		} catch (NullPointerException e) {
			exists = false;
		}
		if (!exists) {
			eGroup = new Entidad();
			eGroup.setNombre("group");
			eGroup.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad("name", group.getName()),
					new Propiedad("admin", Integer.toString(group.getAdmin())),
					new Propiedad("picture", group.getPicture()),
					new Propiedad("members", getAllIds(group.getComponents())))));
			eGroup = server.registrarEntidad(eGroup);
			group.setId(eGroup.getId());
		}
		return (!exists);
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

	private String getAllIds(List<Contacto> list) {
		String result = "";
		for (Contacto c : list) {
			result += c.getId() + " ";
		}
		return result.trim();
	}

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int phone = Integer.parseInt(server.recuperarPropiedadEntidad(eUser, "phone"));
			String username = server.recuperarPropiedadEntidad(eUser, "username");
			String password = server.recuperarPropiedadEntidad(eUser, "password");
			String picture = server.recuperarPropiedadEntidad(eUser, "picture");
			boolean premium = Boolean.parseBoolean(server.recuperarPropiedadEntidad(eUser, "premium"));
			String idListString = server.recuperarPropiedadEntidad(eUser, "contacts");
			String quote = server.recuperarPropiedadEntidad(eUser, "quote");
			List<Integer> idList = new ArrayList<Integer>();
			if (!idListString.equals(""))
				idList = Arrays.stream(idListString.split(" ")).map(Integer::valueOf).collect(Collectors.toList());
			List<Contacto> contacts = getAsContacts(idList);
			Usuario user = new Usuario(id, name, birthday, phone, username, password, picture, premium, contacts,
					quote);
			return user;
		}
		return null;
	}

	public ArrayList<Contacto> getAsContacts(List<Integer> idList) {
		ArrayList<Contacto> result = new ArrayList<Contacto>();
		if (idList != null) {
			for (int id : idList) {
				if (server.recuperarEntidad(id).getNombre().equals("user")) {
					Entidad eUser = server.recuperarEntidad(id);
					String contactName = server.recuperarPropiedadEntidad(eUser, "name");
					String contactPicture = server.recuperarPropiedadEntidad(eUser, "picture");
					Contacto contact = new ContactoIndividual(id, contactName, contactPicture);
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

	@Override
	public ArrayList<Usuario> getAllUsers() {
		ArrayList<Usuario> userList = new ArrayList<Usuario>();
		ArrayList<Entidad> eUserList = server.recuperarEntidades("user");
		for (Entidad eUser : eUserList) {
			userList.add(getUser(eUser.getId()));
		}
		return userList;
	}

	public void deleteUser(Usuario usuario) {

	}

	public void modifyUser(Usuario user) {
		Entidad eUser;
		eUser = server.recuperarEntidad(user.getId());
		for (Propiedad p : eUser.getPropiedades()) {
			if (p.getNombre().equals("picture")) {
				p.setValor(user.getPicture());
			} else if (p.getNombre().equals("quote")) {
				p.setValor(user.getQuote());
			} else if (p.getNombre().equals("contacts")) {
				String contacts = getAllIds(user.getContacts());
				p.setValor(contacts);
			}
			server.modificarPropiedad(p);
		}
	}

	@Override
	public void modifyGroup(Grupo group) {
		Entidad eGroup;
		eGroup = server.recuperarEntidad(group.getId());
		for (Propiedad p : eGroup.getPropiedades()) {
			if (p.getNombre().equals("picture")) {
				p.setValor(group.getPicture());
			} else if (p.getNombre().equals("members")) {
				String contacts = getAllIds(group.getComponents());
				p.setValor(contacts);
			}
			server.modificarPropiedad(p);
		}
	}
}
