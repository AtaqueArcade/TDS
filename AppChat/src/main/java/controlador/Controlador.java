package controlador;

import modelo.Usuario;

public class Controlador {
	private static Controlador instance;
	private Usuario currentuser; //TODO

	public static Controlador getInstance() {
		if (instance == null) {
			instance = new Controlador();
		}
		return instance;
	}

	public boolean login(String user, String password) {
		return false;
	}

	public boolean register(String name, String user, String password, String phone) {
		// TODO registrar usando persistencia, ver ejemplo resuelto
		return true;
	};

}
