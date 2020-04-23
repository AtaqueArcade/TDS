package persistencia;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import beans.Entidad;
import beans.Propiedad;
import modelo.Contacto;
import modelo.ContactoIndividual;
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

	public void registerUser(Usuario user) {
		Entidad eUser;
		boolean exists = true;
		try {
			eUser = server.recuperarEntidad(user.getId());
		} catch (NullPointerException e) {
			exists = false;
		}
		if (exists)
			return;
		// Crear entidad venta
		eUser = new Entidad();
		eUser.setNombre("user");
		eUser.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad("name", user.getName()),
				new Propiedad("birthday", user.getBirthday().toString()),
				new Propiedad("phone", Integer.toString(user.getPhone())),
				new Propiedad("username", user.getUsername()),
				new Propiedad("password", user.getPassword()),
				new Propiedad("picture", Integer.toString(user.getPicture())),
				new Propiedad("premium", String.valueOf(user.isPremium())),
				new Propiedad("contacts", getAllIds(user.getContacts())))));
		eUser = server.registrarEntidad(eUser);
		user.setId(eUser.getId());
	}

	private String getAllIds(List<Contacto> list) {
		String result = "";
		for (Contacto c : list) {
			result += c.getId() + " ";
		}
		return result.trim();
	}

	public Usuario getUser(int id) {
		Entidad eUser = server.recuperarEntidad(id);
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
		int picture = Integer.parseInt(server.recuperarPropiedadEntidad(eUser, "picture"));
		boolean premium = Boolean.parseBoolean(server.recuperarPropiedadEntidad(eUser, "premium"));
		String idListString = server.recuperarPropiedadEntidad(eUser, "contacts");
		List<Integer> idList = new ArrayList<Integer>();
		if (!idListString.equals(""))
			idList = Arrays.stream(idListString.split(" ")).map(Integer::valueOf).collect(Collectors.toList());
		List<Contacto> contacts = getAsContacts(idList);
		Usuario user = new Usuario(name, birthday, phone, username, password, picture, premium, contacts);
		return user;
	}

	public ArrayList<Contacto> getAsContacts(List<Integer> idList) {
		ArrayList<Contacto> result = new ArrayList<Contacto>();
		for (int id : idList) {
			Entidad eUser = server.recuperarEntidad(id);
			String contactName = server.recuperarPropiedadEntidad(eUser, "name");
			int contactPicture = Integer.parseInt(server.recuperarPropiedadEntidad(eUser, "picture"));
			Contacto contact = new ContactoIndividual(id, contactName, contactPicture);
			result.add(contact);
		}
		return result;
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

	public void modifyUser(Usuario usuario) {
	}
}
