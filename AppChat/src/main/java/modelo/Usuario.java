package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {
	private String name;
	private Date birthday;
	private int phone;
	private String username;
	private String password;
	private int picture;
	private boolean premium;
	private ArrayList<Contacto> contacts;
	
	public Usuario(String name, Date birthday, int phone, String username, String password) {
		this.name = name;
		this.birthday = birthday;
		this.phone = phone;
		this.username = username;
		this.password = password;
		picture = 0;
		premium = false;
		contacts = new ArrayList<Contacto>();
	}
	public boolean isUser(String username, String password) {
		if (this.username.equals(username) && this.password.equals(password))
			return true;
		return false;
	}
}
