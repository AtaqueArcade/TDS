package modelo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Usuario {
	private int id;
	private String name;
	private Date birthday;
	private int phone;
	private String username;
	private String password;
	private String picture;
	private String quote;
	private boolean premium;
	private List<Contacto> contacts;

	public Usuario(String name, Date birthday, int phone, String username, String password) {
		id = Id.generateUniqueId();
		this.name = name;
		this.birthday = birthday;
		this.phone = phone;
		this.username = username;
		this.password = password;
		picture = null;
		premium = false;
		contacts = new LinkedList<Contacto>();
	}

	public Usuario(int id, String name, Date birthday, int phone, String username, String password, String picture,
			boolean premium, List<Contacto> contacts, String quote) {
		this(name, birthday, phone, username, password);
		this.id = id;
		this.picture = picture;
		this.premium = premium;
		this.contacts = contacts;
		this.quote = quote;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public List<Contacto> getContacts() {
		return contacts;
	}

	public boolean addContact(Contacto contact) {
		return contacts.add(contact);
	}

	public boolean removeContact(Contacto contact) {
		if (contact instanceof Grupo) {
			Optional<Contacto> group = contacts.stream().filter(c -> c.getMsgId() == contact.getMsgId()).findFirst();
			if (!group.isPresent())
				return false;
			return contacts.remove(group.get());
		}
		return contacts.remove(contact);
	}

	public boolean hasContact(int contactId) {
		if (!contacts.stream().anyMatch(c -> (c.getUserId() == contactId)))
			return true;
		return false;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isUser(String username, String password) {
		if (this.username.equals(username) && this.password.equals(password))
			return true;
		return false;
	}

	public String getUsername() {
		return username;
	}

	public int getId() {
		return id;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getQuote() {
		return this.quote;
	}

	public boolean getPremium() {
		return premium;
	}
}
