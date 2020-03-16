package presistencia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import modelo.Contacto;
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
				new Propiedad("username", user.getUsername()), new Propiedad("nombre", user.getPassword()),
				new Propiedad("picture", Integer.toString(user.getPicture())),
				new Propiedad("premium", String.valueOf(user.isPremium())),
				new Propiedad("contacts", getAllIds(user.getContacts())))));

		eUser = server.registrarEntidad(eUser);
		user.setId(eUser.getId());
	}

	private String getAllIds(ArrayList<Contacto> list) {
		String result = "";
		for (Contacto c : list) {
			result += c.getId() + " ";
		}
		return result.trim();
	}

	public void deleteUser(Usuario usuario) {
	}

	public void modifyUser(Usuario usuario) {
	}

	public Usuario getUser(int codigo) {
		return null;
	}

	public List<Usuario> getAllUsers() {
		return null;
	}
}
