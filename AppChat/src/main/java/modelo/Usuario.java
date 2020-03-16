package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {
	private int id;
	private String name;
	private Date birthday;
	private int phone;
	private String username;
	private String password;
	private int picture;
	private boolean premium;
	private ArrayList<Contacto> contacts;
	
	public Usuario(String name, Date birthday, int phone, String username, String password) {
		id = Id.generateUniqueId();
		this.name = name;
		this.birthday = birthday;
		this.phone = phone;
		this.username = username;
		this.password = password;
		picture = 0;
		premium = false;
		contacts = new ArrayList<Contacto>();
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
	public int getPicture() {
		return picture;
	}
	public void setPicture(int picture) {
		this.picture = picture;
	}
	public boolean isPremium() {
		return premium;
	}
	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	public ArrayList<Contacto> getContacts() {
		return contacts;
	}
	public void setContacts(ArrayList<Contacto> contacts) {
		this.contacts = contacts;
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
	public int getId(){
		return id;
	}
}
