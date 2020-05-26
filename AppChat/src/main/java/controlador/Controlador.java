package controlador;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import TDS.AppChat.App;
import cargador.CargadorMensajes;
import cargador.MensajeEvent;
import cargador.MensajeListener;
import cargador.MensajeWhatsApp;
import modelo.Contacto;
import modelo.ContactoIndividual;
import modelo.D1;
import modelo.D2;
import modelo.Descuento;
import modelo.Grupo;
import modelo.Mensaje;
import modelo.Usuario;

public class Controlador implements MensajeListener {

	private static Controlador instance;
	private CatalogoUsuarios userCatalog;
	private CatalogoGrupos groupCatalog;
	private CatalogoMensajes messageCatalog;
	private Usuario currentUser;
	private Contacto currentContact;
	private CargadorMensajes cm;
	private int contador;

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
		userCatalog = CatalogoUsuarios.getInstance();
		groupCatalog = CatalogoGrupos.getInstance();
		messageCatalog = CatalogoMensajes.getInstance();
	}

	// Registers a new user in the database
	public boolean register(String name, Date birthday, int phone, String username, String password) {
		if (!userCatalog.isUser(username)) {
			Usuario user = new Usuario(name, birthday, phone, username, password);
			if (userCatalog.addUser(user))
				return true;
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
		// Close all opened windows and start up the App again
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
			// it also adds the currentUser as a contact in contactName's
			// contact list
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
	@SuppressWarnings("unlikely-arg-type")
	public boolean deleteContacts(List<Integer> contacts) {
		// deletes current contact on null invocation
		if (contacts == null) {
			messageCatalog.removeMessages(currentUser.getMessages(currentContact));
			userCatalog.getUser(currentContact.getId()).removeContact(currentUser.getId());
			currentUser.removeContact(currentContact.getId());
			userCatalog.modifyUser(currentUser);
			currentContact = null;
			return true;
		}
		// deletes the given list otherwise
		else {
			if (contacts.contains(currentContact))
				currentContact = null;
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
		groupCatalog.addGroup(group);
		userCatalog.modifyUser(currentUser);
		// Inserts the group in each of it's components too
		for (Contacto c : contactList) {
			Usuario user = userCatalog.getUser(c.getId());
			user.addContact(group, msgId);
			userCatalog.modifyUser(user);
		}
		return true;
	}

	// Returns a list of the selected group's components
	public List<String> getGroupComponents(int groupId) {
		Optional<Contacto> g = currentUser.getContacts().stream()
				.filter(c -> ((c.getId() == groupId) && c instanceof Grupo)).findFirst();
		Grupo gr = (Grupo) g.get();
		return gr.getComponents().stream().map(c -> c.getName()).collect(Collectors.toList());
	}

	// Edits the given group if the current user created it
	public boolean editGroup(int groupId, List<String> userNames) {
		Optional<Contacto> g = currentUser.getContacts().stream()
				.filter(c -> ((c.getId() == groupId) && c instanceof Grupo)).findFirst();
		Grupo gr = (Grupo) g.get();
		List<Contacto> oldContacts = gr.getComponents();
		if (gr != null && (gr.getAdmin() == currentUser.getId())) {
			List<Contacto> contactList = currentUser.getContacts().stream().filter(c -> userNames.contains(c.getName()))
					.collect(Collectors.toList());
			gr.setComponents(contactList);
			// Create the group in the new components
			contactList.stream().filter(c -> !oldContacts.contains(c)).forEach(c -> {
				Usuario user = userCatalog.getUser(c.getId());
				user.addContact(gr, currentUser.getMessages(gr));
				userCatalog.modifyUser(user);
			});
			// Delete the group in the deleted users
			oldContacts.stream().filter(c -> !contactList.contains(c)).forEach(c -> {
				Usuario u = userCatalog.getUser(c.getId());
				u.removeContact(groupId);
				userCatalog.modifyUser(u);
			});
			groupCatalog.modifyGroup(gr);
			return true;
		}
		return false;
	}

	// Deletes the selected groups after checking for permissions
	public boolean deleteGroups(List<Integer> groupIds) {
		boolean flag = currentUser.getContacts().stream()
				.filter(c -> c instanceof Grupo && groupIds.contains(c.getId()))
				.allMatch(c -> ((Grupo) c).getAdmin() == currentUser.getId());
		if (flag) {
			for (int groupId : groupIds) {
				Optional<Contacto> g = currentUser.getContacts().stream()
						.filter(c -> ((c.getId() == groupId) && c instanceof Grupo)).findFirst();
				Grupo gr = (Grupo) g.get();
				List<Contacto> oldContacts = gr.getComponents();
				oldContacts.stream().forEach(c -> {
					Usuario u = userCatalog.getUser(c.getId());
					u.removeContact(groupId);
					userCatalog.modifyUser(u);
				});
				currentUser.removeContact(groupId);
				userCatalog.modifyUser(currentUser);
				groupCatalog.removeGroup(groupId);
			}
		}
		return flag;
	}

	// Sets the given group's picture
	public void setGroupPicture(Integer groupId, String url) {
		Optional<Contacto> g = currentUser.getContacts().stream()
				.filter(c -> ((c.getId() == groupId) && c instanceof Grupo)).findFirst();
		Grupo gr = (Grupo) g.get();
		gr.setPicture(url);
		groupCatalog.modifyGroup(gr);
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

	// Get current user's discount
	public Descuento getDiscount() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Descuento discount = null;
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH);
		if (month == Calendar.APRIL)
			discount = new D2();
		else {
			int total = 0;
			for (int msgId : currentUser.getAllMessages())
				for (Mensaje msg : CatalogoMensajes.getInstance().getMessages(msgId))
					if (msg.getSpeaker().equals(currentUser.getName()))
						total++;
			if (total >= 100)
				discount = new D1();
		}
		return discount;
	}

	// Returns the current user's premium status
	public boolean getCurrentUserPremium() {
		return currentUser.getPremium();
	}

	// Sets the current user's premium status
	public void setCurrentUserPremium() {
		currentUser.setPremium(true);
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
		if (currentContact != null && currentUser != null)
			try {
				return messageCatalog.getMessages(currentUser.getMessages(currentContact));
			} catch (NullPointerException e) {
				// Can't stop the view's access while switching users,
				// App will just try again after loading.
				return null;
			}
		return null;
	}

	// Adds a message with the current contact to the message list
	public void addMessageToCurrent(String text, int emoji) {
		Mensaje message = new Mensaje(text, emoji, getCurrentUserName());
		messageCatalog.addMessage(currentUser.getMessages(currentContact), message);
	}

	// Deletes all messages from the current conversation
	public void deleteMessages() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		messageCatalog.removeMessages(currentUser.getMessages(currentContact));
		int msgId = messageCatalog.createMessage();
		currentUser.removeMessages(currentContact, msgId);
		userCatalog.modifyUser(currentUser);
		if (currentContact instanceof Grupo) {
			for (Contacto c : ((Grupo) currentContact).getComponents()) {
				Usuario user = userCatalog.getUser(c.getId());
				user.removeMessages(currentContact, msgId);
				userCatalog.modifyUser(user);
			}
		} else {
			Usuario user = CatalogoUsuarios.getInstance().getUser(currentContact.getId());
			Contacto c = getContact(currentUser.getUsername());
			user.removeMessages(c, msgId);
			userCatalog.modifyUser(user);
		}
	}

	// Retreives data for the statistics
	public int getDataContactAmount() {
		return currentUser.getContacts().size();
	}

	// Retreives data for the statistics
	public int getDataMessagesSent() {
		int contador = 0;
		for (int msgId : currentUser.getAllMessages()) {
			contador += messageCatalog.getMessages(msgId).stream()
					.filter(m -> m.getSpeaker().equals(currentUser.getName())).count();
		}
		return contador;
	}

	// Retreives data for the statistics
	public int getDataMessagesReceived() {
		int contador = 0;
		for (int msgId : currentUser.getAllMessages()) {
			contador += messageCatalog.getMessages(msgId).stream()
					.filter(m -> !m.getSpeaker().equals(currentUser.getName())).count();
		}
		return contador;
	}

	// Retreives data for the statistics
	public Map<String, Integer> getDataBestContacts() {
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		IntStream.range(0, currentUser.getContacts().size()).boxed()
				.collect(Collectors.toMap(currentUser.getContacts()::get, currentUser.getAllMessages()::get)).entrySet()
				.forEach(entry -> {
					if (result.size() < 4)
						result.put(entry.getKey().getName(), messageCatalog.getMessages(entry.getValue()).size());
					else {
						Optional<Entry<String, Integer>> lowestOp = result.entrySet().stream()
								.min((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()));
						if (lowestOp != null) {
							Entry<String, Integer> lowest = lowestOp.get();
							if (lowest.getValue() < messageCatalog.getMessages(entry.getValue()).size()) {
								result.remove(lowest.getKey());
								result.put(entry.getKey().getName(), entry.getValue());
							}
						}
					}
				});
		return result;
	}

	// Retreives data for the statistics
	public int[] getDataMsgReceivedLast30Days() {
		int[] result = new int[30];
		int it = 0;
		for (LocalDate date = LocalDate.now().minusDays(29); date
				.isBefore(LocalDate.now().plusDays(1)); date = date.plusDays(1)) {
			LocalDate day = date;
			contador = 0;
			currentUser.getAllMessages().forEach(msgId -> {
				contador += messageCatalog.getMessages(msgId).stream()
						.filter(m -> (m.getTime().toLocalDate().getDayOfYear() == day.getDayOfYear())
								&& !m.getSpeaker().equals(currentUser.getName()))
						.count();
			});
			result[it] = contador;
			it++;
		}
		return result;
	}

	// Retreives data for the statistics
	public int[] getDataMsgSentLast30Days() {
		int[] result = new int[30];
		int it = 0;
		for (LocalDate date = LocalDate.now().minusDays(29); date
				.isBefore(LocalDate.now().plusDays(1)); date = date.plusDays(1)) {
			LocalDate day = date;
			contador = 0;
			currentUser.getAllMessages().forEach(msgId -> {
				contador += messageCatalog.getMessages(msgId).stream()
						.filter(m -> (m.getTime().toLocalDate().getDayOfYear() == day.getDayOfYear())
								&& m.getSpeaker().equals(currentUser.getName()))
						.count();
			});
			result[it] = contador;
			it++;
		}
		return result;
	}

	// Retreives data for the statistics
	public int[] getDataMsgPerDay() {
		int[] result = new int[24];
		int it = 0;
		for (LocalTime hour = LocalTime.now().with(LocalTime.MIN); hour
				.isBefore(LocalTime.now().with(LocalTime.MAX).minusHours(1)); hour = hour.plusHours(1)) {
			LocalTime hourOfDay = hour;
			contador = 0;
			currentUser.getAllMessages().forEach(msgId -> {
				contador += messageCatalog.getMessages(msgId).stream()
						.filter(m -> (m.getTime().toLocalTime().getHour() == hourOfDay.getHour())
								&& m.getSpeaker().equals(currentUser.getName()))
						.count();
			});
			result[it] = contador;
			it++;
		}
		return result;
	}

	// JavaBeans function. Calls the message parser
	public void importMessages(String absolutePath, int n) {
		if (cm == null) {
			cm = new CargadorMensajes();
			cm.addMensajeListener(this);
		}
		cm.setFichero(absolutePath, n);
	}

	// JavaBeans function. 'NuevosMensajes()' method
	@Override
	public void enteradoCambioMensaje(EventObject event) {
		List<MensajeWhatsApp> newMsgs = ((MensajeEvent) event).getNewMensajes();
		List<MensajeWhatsApp> oldMsgs = ((MensajeEvent) event).getOldMensajes();
		// Makes no sense to identify the contact as the person's actual name!
		// only barely works in 1 on 1 conversations, no way to identify groups
		// also SimpleTextParser shoots an exception if given the wrong format!
		// working with what I've been given
		if (!newMsgs.equals(oldMsgs)) {
			Optional<MensajeWhatsApp> WAmsg = newMsgs.stream()
					.filter(msg -> !msg.getAutor().equals(currentUser.getName())).findFirst();
			if (WAmsg != null) {
				String contactName = WAmsg.get().getAutor();
				Optional<Contacto> contact = currentUser.getContacts().stream()
						.filter(c -> c.getName().equals(contactName)).findFirst();
				if (contact.isPresent()) {
					newMsgs.stream().forEach(msg -> {
						String speaker = null;
						if (msg.getAutor().equals(currentUser.getName()))
							speaker = currentUser.getName();
						else if (msg.getAutor().equals(contactName))
							speaker = contactName;
						if (speaker != null) {
							Mensaje message = new Mensaje(msg.getTexto(), 0, speaker);
							messageCatalog.addMessage(currentUser.getMessages(contact.get()), message);
						}
					});
				}
			}
		}
	}
}