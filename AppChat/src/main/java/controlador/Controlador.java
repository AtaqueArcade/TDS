package controlador;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import modelo.Contacto;
import modelo.ContactoIndividual;
import modelo.Grupo;
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

	public void logOut() {
		// TODO Auto-generated method stub
	}

	public Contacto getContact(String username)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Contacto response;
		Usuario user = CatalogoUsuarios.getInstance().getUser(username);
		if (user != null) {
			response = new ContactoIndividual(user.getName(), user.getPicture());
			return response;

		}
		return null;
	}

	public boolean addContact(String contactName)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Usuario user = null;
		try {
			user = CatalogoUsuarios.getInstance().getUser(contactName);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user != null) {
			Contacto c = getContact(contactName);
			return currentUser.addContact(c);
		}
		return false;
	}

	public boolean addContact(String groupName, List<String> userNames) {
		List<Contacto> contactList = currentUser.getContacts().stream().filter(c -> userNames.contains(c.getName()))
				.collect(Collectors.toList());
		Grupo group = new Grupo(groupName, currentUser, contactList);
		return currentUser.addContact(group);
	}

	public boolean checkContactList(List<String> contacts) {
		boolean flag = true;
		List<String> contactList = currentUser.getContacts().stream().map(Contacto::getName)
				.collect(Collectors.toList());
		for (String name : contacts)
			if (contactList.contains(name))
				flag = false;
		return flag;
	}

	public String getCurrentUser() {
		return currentUser.getName();
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

	public boolean deleteContacts(List<String> contacts) {
		currentUser.getContacts().removeIf(c -> contacts.contains(c.getName()));
		return true;
	}

	public List<String> getUsernamesByFilter(String filter)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<String> usernames = CatalogoUsuarios.getInstance().getByFilter(filter);
		usernames.remove(currentUser.getUsername());
		return usernames;
	}

	public List<String> getGroupComponents(String groupName) {
		Optional<Contacto> g = currentUser.getContacts().stream()
				.filter(c -> (c.getName().equals(groupName) && c instanceof Grupo)).findFirst();
		Grupo gr = (Grupo) g.get();
		return gr.getComponents().stream().map(c -> c.getName()).collect(Collectors.toList());
	}

	public boolean deleteGroups(List<String> groupNames) {
		boolean flag = currentUser.getContacts().stream().filter(c -> c instanceof Grupo)
				.allMatch(c -> ((Grupo) c).getAdmin().equals(currentUser));
		if (flag)
			currentUser.getContacts().removeIf(c -> c instanceof Grupo && ((Grupo) c).getAdmin().equals(currentUser)
					&& groupNames.contains(c.getName()));
		return flag;
	}

	public boolean editGroup(String groupName, List<String> userNames) {
		Optional<Contacto> g = currentUser.getContacts().stream()
				.filter(c -> (c.getName().equals(groupName) && c instanceof Grupo)).findFirst();
		Grupo gr = (Grupo) g.get();
		if (gr != null && (gr.getAdmin() == currentUser)) {
			List<Contacto> contactList = currentUser.getContacts().stream().filter(c -> userNames.contains(c.getName()))
					.collect(Collectors.toList());
			gr.setComponents(contactList);
			return true;
		}
		return false;
	}
}