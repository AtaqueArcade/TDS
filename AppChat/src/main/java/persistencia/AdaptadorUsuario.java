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
					new Propiedad("contacts", getContactsAsString(user.getContacts())),
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
			List<Contacto> contacts = getStringAsContacts(idList);
			Usuario user = new Usuario(id, name, birthday, phone, username, password, picture, premium, contacts,
					quote);
			return user;
		}
		return null;
	}

	private List<Contacto> getStringAsContacts(List<Integer> idList) {
		// TODO Auto-generated method stub
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
				String contacts = getContactsAsString(user.getContacts());
				p.setValor(contacts);
			} else if (p.getNombre().equals("premium")) {
				String premium = Boolean.toString(user.getPremium());
				p.setValor(premium);
			}
			server.modificarPropiedad(p);
		}
	}

	public void deleteAll() {
		ArrayList<Entidad> eUserList = server.recuperarEntidades("user");
		for (Entidad eUser : eUserList) {
			server.borrarEntidad(eUser);
		}
	}

	// Supporting methods
	private String getContactsAsString(List<Contacto> contacts) {
		String result = "";
		for (Contacto c : contacts) {
			result += c.getId() + " ";
		}
		return result.trim();
	}

	private List<Contacto> getStringAsContacts(String idString) {
		/*
		 * List<Contacto> result = null; if (!idString.equals("")) result =
		 * Arrays.stream(idString.split(" ")).map(id -> getContact(Integer.valueOf(id)))
		 * .collect(Collectors.toList()); return result;
		 */
	}
}
