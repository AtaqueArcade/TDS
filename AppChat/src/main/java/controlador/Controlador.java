package controlador;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import TDS.AppChat.App;
import modelo.Contacto;
import modelo.ContactoIndividual;
import modelo.Grupo;
import modelo.Mensaje;
import modelo.Usuario;
import persistencia.DAOusuario;
import persistencia.FactoriaDAO;

public class Controlador {

	private static Controlador instance;
	private FactoriaDAO dao;
	private DAOusuario userAdapter;
	private CatalogoUsuarios userCatalog;
	private CatalogoMensajes messageCatalog;
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

	// Starts up all the catalogues
	private void initialize() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		dao = FactoriaDAO.getInstance();
		userAdapter = dao.getDAOusuario();
		userCatalog = CatalogoUsuarios.getInstance();
		messageCatalog = CatalogoMensajes.getInstance();
	}

	// Registers a new user in the database
	public boolean register(String name, Date birthday, int phone, String username, String password) {
		if (!userCatalog.isUser(username)) {
			Usuario user = new Usuario(name, birthday, phone, username, password);
			if (userAdapter.registerUser(user)) {
				userCatalog.addUser(username, user);
				return true;
			}
		}
		return false;
	}

	// Checks if the user exists in the database
	public boolean login(String username, String password) {
		if (userCatalog.match(username, password)) {
			currentUser = userCatalog.getUser(username);
			return true;
		}
		return false;
	}

	// Resets the current user
	public void logOut() {
		// Close all opened windows and start the App again
		java.awt.Window win[] = java.awt.Window.getWindows();
		for (int i = 0; i < win.length; i++) {
			win[i].dispose();
		}
		App.main(null);
	}

	// Returns all hits of usernames in the user database
	public List<String> getUsernamesByFilter(String filter)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<String> usernames = CatalogoUsuarios.getInstance().getByFilter(filter);
		usernames.remove(currentUser.getUsername());
		return usernames;
	}

	// Returns the picture of any user
	public String getUserPicture(int id) {
		return userCatalog.getUser(id).getPicture();
	}

	// Returns the username as a Contact if it's registered in the database
	public Contacto getContact(String username)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Contacto response;
		Usuario user = CatalogoUsuarios.getInstance().getUser(username);
		if (user != null) {
			response = new ContactoIndividual(user.getId(), user.getName(), user.getPicture(), user.getPhone());
			return response;
		}
		return null;
	}

	// Adds a contact to the current user
	public boolean addContact(String contactName)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Usuario user = null;
		try {
			user = CatalogoUsuarios.getInstance().getUser(contactName);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (user != null) {
			Contacto c1 = getContact(contactName);
			if (currentUser.hasContact(c1.getId()))
				return false;
			int msgId = messageCatalog.createMessage();
			currentUser.addContact(c1, msgId);
			userCatalog.modifyUser(currentUser);
			Contacto c2 = getContact(currentUser.getUsername());
			user.addContact(c2, msgId);
			userCatalog.modifyUser(user);
			return true;
		}
		return false;
	}

	// Deletes the selected contacts from the current user's list
	public boolean deleteContacts(List<Integer> contacts) {
		// delete current contact
		if (contacts == null) {
			messageCatalog.removeMessages(currentUser.getMessages(currentContact));
			userCatalog.getUser(currentContact.getId()).removeContact(currentUser.getId());
			currentUser.removeContact(currentContact.getId());
			userCatalog.modifyUser(currentUser);
			return true;
		}
		// delete contact list
		else {
			currentUser.getContacts().stream()
					.filter(c -> contacts.contains(c.getId()) && (userCatalog.getUser(c.getId()) != null))
					.forEach(c -> {
						messageCatalog.removeMessages(currentUser.getMessages(c));
						userCatalog.getUser(c.getId()).removeContact(currentUser.getId());
						userCatalog.modifyUser(userCatalog.getUser(c.getId()));
					});
			contacts.forEach(id -> currentUser.removeContact(id));
			userCatalog.modifyUser(currentUser);
			return true;
		}
	}

	// Adds a group contact to the current user
	public boolean addContact(String groupName, List<String> userNames)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException {
		List<Contacto> contactList = currentUser.getContacts().stream().filter(c -> userNames.contains(c.getName()))
				.collect(Collectors.toList());
		Grupo group = new Grupo(groupName, currentUser.getId(), contactList);
		int msgId = messageCatalog.createMessage();
		currentUser.addContact(group, msgId);
		userAdapter.registerGroup(group);
		userCatalog.modifyUser(currentUser);
		for (Contacto c : contactList) {
			Usuario user = userCatalog.getUser(c.getId());
			user.addContact(group, msgId);
			userCatalog.modifyUser(user);
		}
		return true;
	}

	// Returns a list of the selected group's components
	public List<String> getGroupComponents(String groupName) {
		Optional<Contacto> g = currentUser.getContacts().stream()
				.filter(c -> (c.getName().equals(groupName) && c instanceof Grupo)).findFirst();
		Grupo gr = (Grupo) g.get();
		return gr.getComponents().stream().map(c -> c.getName()).collect(Collectors.toList());
	}

	// Edits the given groups if the current user created it
	public boolean editGroup(String groupName, List<String> userNames) {
		Optional<Contacto> g = currentUser.getContacts().stream()
				.filter(c -> (c.getName().equals(groupName) && c instanceof Grupo)).findFirst();
		Grupo gr = (Grupo) g.get();
		if (gr != null && (gr.getAdmin() == currentUser.getId())) {
			List<Contacto> contactList = currentUser.getContacts().stream().filter(c -> userNames.contains(c.getName()))
					.collect(Collectors.toList());
			gr.setComponents(contactList);
			userAdapter.modifyGroup(gr);
			userCatalog.modifyUser(currentUser);
			return true;
		}
		return false;
	}

	// deletes the selected groups
	public boolean deleteGroups(List<String> groupNames) {
		boolean flag = currentUser.getContacts().stream().filter(c -> c instanceof Grupo)
				.allMatch(c -> ((Grupo) c).getAdmin() == currentUser.getId());
		if (flag)
			currentUser.getContacts().removeIf(c -> c instanceof Grupo
					&& (((Grupo) c).getAdmin() == currentUser.getId()) && groupNames.contains(c.getName()));
		userCatalog.modifyUser(currentUser);
		return flag;
	}

	// Sets the selected group's picture
	public void setGroupPicture(String groupName, String url) {
		//
	}

	// Checks if usernames are already in the current user's contact list
	public boolean checkContactList(List<String> contacts) {
		boolean flag = true;
		List<String> contactList = currentUser.getContacts().stream().map(Contacto::getName)
				.collect(Collectors.toList());
		for (String name : contacts)
			if (contactList.contains(name))
				flag = false;
		return flag;
	}

	// Returns the current user's name
	public String getCurrentUserName() {
		return currentUser.getName();
	}

	// Returns the current user's picture
	public String getCurrentUserPicture() {
		return currentUser.getPicture();
	}

	// Sets the current user's picture
	public void setCurrentUserPicture(String picture) {
		currentUser.setPicture(picture);
		userCatalog.modifyUser(currentUser);
	}

	// Returns the current user's quote
	public String getCurrentUserQuote() {
		String quote = currentUser.getQuote();
		if (quote == null)
			return "Hello! I'm using AppChat";
		return quote;
	}

	// Sets the current user's quote
	public void setCurrentUserQuote(String quote) {
		currentUser.setQuote(quote);
		userCatalog.modifyUser(currentUser);
	}

	// Returns the current user's contact list
	public List<Contacto> getCurrentContacts() {
		return currentUser.getContacts();
	}

	// Points out if there's a contact selected
	public boolean isContactSelected() {
		return (currentContact != null);
	}

	// Changes all the chat assets to the selected contact in the user's list
	public void setCurrentChat(Contacto contact) {
		currentContact = contact;
	}

	// Returns the current contact's name
	public String getCurrentContactName() {
		if (currentContact == null)
			return null;
		return currentContact.getName();
	}

	// Returns the current contact's phone
	public String getCurrentContactPhone() {
		if (currentContact == null)
			return null;
		return currentContact.getPhone();
	}

	// Returns the current contact's picture
	public String getCurrentContactPicture() {
		if (currentContact == null)
			return null;
		return currentContact.getPicture();
	}

	// Returns the current message list
	public List<Mensaje> getCurrentMessages() {
		if (currentContact != null)
			return messageCatalog.getMessages(currentUser.getMessages(currentContact));
		return null;
	}

	// Adds a message to the message list
	public void addMessageToCurrent(String text, int emoji) {
		Mensaje message = new Mensaje(text, emoji, getCurrentUserName());
		messageCatalog.addMessage(currentUser.getMessages(currentContact), message);
	}

	// Deletes all messages from the current conversation
	public void deleteMessages() {
		messageCatalog.removeMessages(currentContact.getId());
		userCatalog.modifyUser(currentUser);
	}
}