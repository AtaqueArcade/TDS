package controlador;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.Icon;

import modelo.Contacto;
import modelo.ContactoIndividual;
import modelo.Usuario;
import persistencia.DAOusuario;
import persistencia.FactoriaDAO;

public class Controlador {

	private static Controlador instance;
	private FactoriaDAO dao;
	private DAOusuario userAdapter;
	private CatalogoUsuarios userCatalog;
	
	private Usuario currentUser;
	private Contacto currentContact;

	public static Controlador getInstance()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null) {
			instance = new Controlador();
			instance.initialize();
		}
		return instance;
	}

	private void initialize() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		dao = FactoriaDAO.getInstance();
		userAdapter = dao.getDAOusuario();
		userCatalog = CatalogoUsuarios.getInstance();
	}

	public boolean register(String name, Date birthday, int phone, String username, String password) {
		if (!userCatalog.isUser(username)) {
			Usuario user = new Usuario(name, birthday, phone, username, password);
			userAdapter.registerUser(user);
			userCatalog.addUser(username, user);
			return true;
		}
		return false;
	}

	public boolean login(String username, String password) {
		if (userCatalog.match(username, password)) {
			currentUser = userCatalog.getUser(username);
			return true;
		}
		return false;
	}

	public String getcurrentUser() {
		return currentUser.getName();
	}

	public void logOut() {
		// TODO Auto-generated method stub
	}

	public List<String> getUsernamesByFilter(String filter)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<String> usernames = CatalogoUsuarios.getInstance().getByFilter(filter);
		usernames.remove(currentUser.getUsername());
		return usernames;
	}

	public Contacto getContact(String username)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Usuario user = CatalogoUsuarios.getInstance().getUser(username);
		Contacto response = new ContactoIndividual(user.getName(), user.getPicture());
		return response;
	}

	public boolean addContact(String username) {
		Contacto contact = null;
		try {
			contact = getContact(username);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentUser.addContact(contact);
	}

	public boolean checkContactList(List<String> contacts) {
		boolean flag = true;
		List<String> usernameList = currentUser.getContacts().stream().map(Contacto::getName)
				.collect(Collectors.toList());
		for (String username : contacts)
			if (usernameList.contains(username))
				flag = false;
		return flag;
	}

	public List<Contacto> getCurrentContacts() {
		return currentUser.getContacts();
	}

	public void setCurrentChat(Contacto contact) {
		currentContact = contact;
	}

	public Contacto getCurrentContact() {
		return currentContact;
	}
}
