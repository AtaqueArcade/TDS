package controlador;

import modelo.Usuario;

public class Controlador {
	private static Controlador unicaInstancia;
	private Usuario currentuser;
	
	public static Controlador getUnicaInstancia() {
		if (unicaInstancia == null){
			unicaInstancia = new Controlador();
		}
		return unicaInstancia;
	}
	
	public boolean login(String user, String password){
		return false;
	}
}
