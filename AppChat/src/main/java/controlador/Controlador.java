package controlador;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
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
import modelo.Id;
import modelo.Mensaje;
import modelo.Usuario;
import persistencia.AdaptadorContacto;
import persistencia.AdaptadorMensajes;
import persistencia.DAOcontacto;
import persistencia.DAOmensajes;

public class Controlador implements MensajeListener {

	private static Controlador instance;
	private CatalogoUsuarios userCatalog;
	private DAOcontacto contactDAO;
	private DAOmensajes messageDAO;
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
		contactDAO = AdaptadorContacto.getInstance();
		messageDAO = AdaptadorMensajes.getInstance();
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
		currentUser = null;
		currentContact = null;
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

	// Adds a contact to a user
	public boolean addContact(Usuario user1, String contactName) {
		if (user1 == null)
			if (currentUser != null)
				user1 = currentUser;
			else
				return false;
		Usuario user2 = null;
		try {
			user2 = CatalogoUsuarios.getInstance().getUser(contactName);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (user2 != null) {
			if (user1.hasContact(user2.getId()))
				return false;
			int msgId = messageDAO.createMessageList();
			List<Mensaje> messageList = messageDAO.getMessageList(msgId);
			// adds user2 as a contact in user1
			int newId = Id.generateUniqueId();
			Contacto contact1 = new ContactoIndividual(newId, msgId, user2.getId(), user2.getName(), user2.getPicture(),
					user2.getPhone());
			contact1.setMessages(messageList);
			contactDAO.registerContact(contact1);
			user1.addContact(contact1);
			userCatalog.modifyUser(user1);
			// adds the user1 as a contact in user2
			newId = Id.generateUniqueId();
			Contacto contact2 = new ContactoIndividual(newId, msgId, user1.getId(), user1.getName(), user1.getPicture(),
					user1.getPhone());
			contact2.setMessages(messageList);
			contactDAO.registerContact(contact2);
			user2.addContact(contact2);
			userCatalog.modifyUser(user2);
			return true;
		}
		return false;
	}

	// Deletes the selected contact from the current user's list
	public boolean deleteContact(Contacto contact) {
		// deletes current contact on null invocation
		if (contact == null) {
			if (currentContact == null)
				return false;
			contact = currentContact;
			currentContact = null;
		}
		Usuario user = userCatalog.getUser(contact.getUserId());
		Optional<Contacto> result = user.getContacts().stream().filter(c -> c.getUserId() == currentUser.getId())
				.findFirst();
		if (!result.isPresent())
			return false;
		messageDAO.deleteMessageList(contact.getMsgId());
		contactDAO.deleteContact(result.get());
		contactDAO.deleteContact(contact);
		user.removeContact(result.get());
		currentUser.removeContact(contact);
		userCatalog.modifyUser(user);
		userCatalog.modifyUser(currentUser);
		return true;
	}

	// Deletes the selected contacts from the current user's list
	public boolean deleteContact(List<Integer> ids) {
		if (ids == null || ids.size() == 0)
			return false;
		if (ids.contains(currentContact.getId()))
			currentContact = null;
		List<Contacto> contacts = currentUser.getContacts().stream()
				.filter(c -> ids.contains(c.getId()) && (c instanceof ContactoIndividual)).collect(Collectors.toList());
		contacts.stream().forEach(c -> {
			deleteContact(c);
		});
		return true;
	}

	// Adds a group contact to the current user
	public boolean addContact(String groupName, List<Integer> ids) {
		List<Contacto> components = currentUser.getContacts().stream().filter(c -> ids.contains(c.getId()))
				.collect(Collectors.toList());
		int newId = Id.generateUniqueId();
		int msgId = messageDAO.createMessageList();
		Contacto group = new Grupo(newId, msgId, 0, groupName, null, currentUser.getId(), components);
		group.setMessages(messageDAO.getMessageList(msgId));

		// adds the group as a contact in currentUser
		contactDAO.registerContact(group);
		currentUser.addContact(group);
		userCatalog.modifyUser(currentUser);

		// Inserts the group in each of it's components
		components.stream().forEach(component -> {
			Usuario user = userCatalog.getUser(component.getUserId());
			user.addContact(group);
			userCatalog.modifyUser(user);
		});
		contactDAO.registerContact(group);
		return true;
	}

	// Returns a list of information of the selected group's components
	public Map<String, String> getGroupComponents(int groupId) {
		Optional<Contacto> g = currentUser.getContacts().stream()
				.filter(c -> ((c.getId() == groupId) && c instanceof Grupo)).findFirst();
		Grupo gr = (Grupo) g.get();
		return gr.getComponents().stream().collect(Collectors.toMap(Contacto::getName, Contacto::getPhone));
	}

	// Edits the given group if the current user created it
	public boolean editGroup(int groupId, List<Integer> ids) {
		Optional<Contacto> g = currentUser.getContacts().stream()
				.filter(c -> ((c.getId() == groupId) && c instanceof Grupo)).findFirst();
		if (g.isPresent()) {
			Grupo group = (Grupo) g.get();
			if (group.getAdmin() == currentUser.getId()) {
				List<Contacto> oldContacts = group.getComponents();
				List<Contacto> newContacts = currentUser.getContacts().stream().filter(c -> ids.contains(c.getId()))
						.collect(Collectors.toList());
				group.setComponents(newContacts);

				// Delete the old group in all the contacts
				oldContacts.stream().forEach(c -> {
					Usuario user = userCatalog.getUser(c.getUserId());
					user.removeContact(group);
					userCatalog.modifyUser(user);
				});
				// Inserts the group in each of it's components
				newContacts.stream().forEach(component -> {
					Usuario user = userCatalog.getUser(component.getUserId());
					user.addContact(group);
					userCatalog.modifyUser(user);
				});
				contactDAO.modifyContact(group);
				return true;
			}
		}
		return false;
	}

	// Deletes the selected groups after checking for permissions
	public boolean deleteGroups(List<Integer> groupIds) {
		if (currentUser.getContacts().stream().filter(c -> c instanceof Grupo && groupIds.contains(c.getId()))
				.allMatch(c -> ((Grupo) c).getAdmin() == currentUser.getId())) {
			for (int groupId : groupIds) {
				Optional<Contacto> g = currentUser.getContacts().stream()
						.filter(c -> ((c.getId() == groupId) && c instanceof Grupo)).findFirst();
				Grupo group = (Grupo) g.get();
				currentUser.removeContact(group);
				userCatalog.modifyUser(currentUser);
				// Deletes the group in all the contacts
				group.getComponents().stream().forEach(c -> {
					Usuario user = userCatalog.getUser(c.getUserId());
					user.removeContact(group);
					userCatalog.modifyUser(user);
				});
				messageDAO.deleteMessageList(group.getMsgId());
				contactDAO.deleteContact(group);
			}
			return true;
		}
		return false;
	}

	// Sets the given group's picture
	public void setGroupPicture(Integer groupId, String url) {
		Optional<Contacto> g = currentUser.getContacts().stream()
				.filter(c -> ((c.getId() == groupId) && c instanceof Grupo)).findFirst();
		Grupo gr = (Grupo) g.get();
		gr.setPicture(url);
		contactDAO.modifyContact(gr);
		// Update all group versions
		gr.getComponents().stream().forEach(c -> {
			Usuario user = userCatalog.getUser(c.getId());
			user.getContacts().stream()
					.filter(contact -> contact instanceof Grupo && (contact.getMsgId() == gr.getMsgId()))
					.forEach(group -> {
						gr.setPicture(url);
						contactDAO.modifyContact(group);
					});
		});
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

	// Checks if the given Id belongs to the current user
	public boolean isCurrentUser(int id) {
		return (currentUser.getId() == id);
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

	// Returns the current user's premium status
	public boolean getCurrentUserPremium() {
		return currentUser.getPremium();
	}

	// Sets the current user's premium status
	public void setCurrentUserPremium() {
		currentUser.setPremium(true);
		userCatalog.modifyUser(currentUser);
	}

	// Get current user's discount
	public Descuento getDiscount() {
		Descuento discount = null;
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH);
		if (month == Calendar.APRIL)
			discount = new D2();
		else {
			int total = 0;
			for (int msgId : currentUser.getContacts().stream().map(Contacto::getMsgId).collect(Collectors.toList()))
				for (Mensaje msg : messageDAO.getMessageList(msgId))
					if (msg.getSpeaker() == currentUser.getId())
						total++;
			if (total >= 100)
				discount = new D1();
		}
		return discount;
	}

	// Points out if there's a contact selected
	public boolean isContactSelected() {
		return (currentContact != null);
	}

	// Returns the current user's contact information
	public Map<String, Integer> getCurrentContacts() {
		currentUser = userCatalog.getUser(currentUser.getId());
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		try {
			currentUser.getContacts().stream().forEach(contact -> {
				result.put(contact.getName(), contact.getId());
			});
		} catch (NullPointerException e) {
			// Can't stop the view's access while switching users,
			// App will just try again after loading.
			return null;
		}
		return result;
	}

	// Changes all the chat assets to the selected contact in the user's list
	public void setCurrentChat(int id) {
		Optional<Contacto> contact = currentUser.getContacts().stream().filter(c -> c.getId() == id).findFirst();
		if (contact.isPresent())
			currentContact = contact.get();
	}

	// Checks the current contact's class
	public boolean isContactoIndividual(int id) {
		Optional<Contacto> contact = currentUser.getContacts().stream().filter(c -> c.getId() == id).findFirst();
		if (contact.isPresent())
			return contact.get() instanceof ContactoIndividual;
		return false;
	}

	// Checks the current contact's class
	public boolean isGrupo(int id) {
		Optional<Contacto> contact = currentUser.getContacts().stream().filter(c -> c.getId() == id).findFirst();
		if (contact.isPresent())
			return contact.get() instanceof Grupo;
		return false;
	}

	// Returns a contact's name based on it's user id. Needed for group chat
	// messages, where the contact isn't always in the current user's contact list
	public String getName(int id) {
		Optional<Contacto> contact = currentUser.getContacts().stream().filter(c -> c.getUserId() == id).findFirst();
		if (contact.isPresent())
			return contact.get().getName();
		return userCatalog.getUser(id).getName();
	}

	// Returns the contact's name based on it's contact id. Needed for contact
	// lists, where the only information available for the view is the contact's Id
	public String getContactName(Integer id) {
		// On 0 invocation the current contact's will be returned
		if (id == 0) {
			if (currentContact == null)
				return null;
			return currentContact.getName();
		}
		Optional<Contacto> result = currentUser.getContacts().stream().filter(c -> c.getId() == id).findFirst();
		if (result.isPresent())
			return result.get().getName();
		return null;
	}

	// Changes the selected contact's alias for the current user
	public boolean changeContactName(Integer id, String name) {
		Optional<Contacto> contact = currentUser.getContacts().stream().filter(c -> c.getId() == id).findFirst();
		if (!contact.isPresent())
			return false;
		if (contact.get() instanceof Grupo)
			return false;
		contact.get().setName(name);
		contactDAO.modifyContact(contact.get());
		userCatalog.modifyUser(currentUser);
		return true;
	}

	// Returns the contact's phone
	public String getContactPhone(Integer id) {
		// On 0 invocation the current contact's will be returned
		if (id == 0) {
			if (currentContact == null)
				return null;
			return currentContact.getPhone();
		}
		Optional<Contacto> result = currentUser.getContacts().stream().filter(c -> c.getId() == id).findFirst();
		if (result.isPresent())
			return result.get().getPhone();
		return null;
	}

	// Returns the contact's picture
	public String getContactPicture(Integer id) {
		// On 0 invocation the current contact's will be returned
		if (id == 0) {
			if (currentContact == null)
				return null;
			return currentContact.getPicture();
		}
		Optional<Contacto> result = currentUser.getContacts().stream().filter(c -> c.getId() == id).findFirst();
		if (result.isPresent())
			return result.get().getPicture();
		return null;
	}

	// Returns the current message list
	public List<Mensaje> getCurrentMessages() {
		if (currentContact != null && currentUser != null)
			try {
				currentContact = contactDAO.getContact(currentContact.getId());
				return currentContact.getMessages();
			} catch (NullPointerException e) {
				// Can't stop the view's access while switching users,
				// App will just try again after loading.
				return null;
			}
		return null;
	}

	// Adds a message to a contact's message list
	public void addMessage(String text, int emoji, Contacto con) {
		// If null is passed in con, msg will be added to the current contact
		Contacto contact;
		if (con == null)
			contact = contactDAO.getContact(currentContact.getId());
		else
			contact = contactDAO.getContact(con.getId());
		Mensaje message = new Mensaje(text, emoji, currentUser.getId());
		List<Mensaje> msgList = contact.getMessages();
		msgList.add(message);
		messageDAO.modifyMessageList(contact.getMsgId(), msgList);
		contact.setMessages(msgList);
		// Groups share the same contact, only single contacts have to be updated
		if (contact instanceof ContactoIndividual) {
			Usuario user = userCatalog.getUser(contact.getUserId());
			Optional<Contacto> result = user.getContacts().stream().filter(c -> c.getUserId() == currentUser.getId())
					.findFirst();
			if (!result.isPresent())
				return;
			result.get().setMessages(msgList);
		}
	}

	// Deletes all messages from the current conversation
	public void deleteMessages() {
		messageDAO.modifyMessageList(currentContact.getMsgId(), new LinkedList<Mensaje>());
		currentContact.removeMessages();
		contactDAO.modifyContact(currentContact);
		// Groups share the message list, only single contacts has to be updated
		if (currentContact instanceof ContactoIndividual) {
			Usuario user = userCatalog.getUser(currentContact.getUserId());
			Optional<Contacto> result = user.getContacts().stream().filter(c -> c.getUserId() == currentUser.getId())
					.findFirst();
			if (!result.isPresent())
				return;
			messageDAO.modifyMessageList(result.get().getMsgId(), new LinkedList<Mensaje>());
			result.get().removeMessages();
			contactDAO.modifyContact(result.get());
		}
	}

	// Retreives data for the statistics
	public int getDataContactAmount() {
		return currentUser.getContacts().size();
	}

	// Retreives data for the statistics
	public int getDataMessagesSent() {
		return currentContact.getMsgsByUser(currentUser.getId());
	}

	// Retreives data for the statistics
	public int getDataMessagesReceived() {
		int contador = 0;
		for (Contacto c : currentUser.getContacts()) {
			contador += (c.getMessages().size() - c.getMsgsByUser(currentUser.getId()));
		}
		return contador;
	}

	// Retreives data for the statistics
	public Map<String, Integer> getDataBestContacts() {
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		currentUser.getContacts().stream().forEach(contact -> {
			if (result.size() < 4)
				result.put(contact.getName(), contact.getMessages().size());
			else {
				Optional<Entry<String, Integer>> lowestOp = result.entrySet().stream()
						.min((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()));
				if (lowestOp != null) {
					Entry<String, Integer> lowest = lowestOp.get();
					if (lowest.getValue() < contact.getMessages().size()) {
						result.remove(lowest.getKey());
						result.put(contact.getName(), contact.getMessages().size());
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
			currentUser.getContacts().forEach(contacto -> {
				contador += contacto.getMessages().stream()
						.filter(m -> (m.getTime().toLocalDate().getDayOfYear() == day.getDayOfYear())
								&& !(m.getSpeaker() == currentUser.getId()))
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
			currentUser.getContacts().forEach(contact -> {
				contador += contact.getMessages().stream()
						.filter(m -> (m.getTime().toLocalDate().getDayOfYear() == day.getDayOfYear())
								&& (m.getSpeaker() == currentUser.getId()))
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
			currentUser.getContacts().forEach(contact -> {
				contador += contact.getMessages().stream()
						.filter(m -> (m.getTime().toLocalTime().getHour() == hourOfDay.getHour())
								&& (m.getSpeaker() == currentUser.getId()))
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
		if (!newMsgs.equals(oldMsgs)) {
			Optional<MensajeWhatsApp> WAmsg = newMsgs.stream()
					.filter(msg -> !msg.getAutor().equals(currentUser.getName())).findFirst();
			if (WAmsg != null) {
				String contactName = WAmsg.get().getAutor();
				Optional<Contacto> contact = currentUser.getContacts().stream()
						.filter(c -> c.getName().equals(contactName)).findFirst();
				if (contact.isPresent()) {
					newMsgs.stream().forEach(msg -> {
						int speakerId = 0;
						if (msg.getAutor().equals(currentUser.getName()))
							speakerId = currentUser.getId();
						else if (msg.getAutor().equals(contactName))
							speakerId = contact.get().getId();
						if (speakerId != 0) {
							addMessage(msg.getTexto(), 0, contact.get());
						}
					});
				}
			}
		}
	}
}