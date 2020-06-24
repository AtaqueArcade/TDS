package junit;

import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import controlador.Controlador;
import modelo.Mensaje;
import modelo.Usuario;
import persistencia.DAOmensajes;
import persistencia.DAOcontacto;
import persistencia.DAOusuario;
import persistencia.FactoriaDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class JUnitTest {

	private static FactoriaDAO dao;
	private static DAOusuario adaptadorUsuario;
	private static DAOmensajes adaptadorMensajes;
	private static DAOcontacto adaptadorContactos;
	private static Usuario u1;
	private static Usuario u2;

	@BeforeClass
	public static void initialize() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		dao = FactoriaDAO.getInstance();
		adaptadorUsuario = dao.getDAOusuario();
		adaptadorMensajes = dao.getDAOmensajes();
		adaptadorContactos = dao.getDAOcontactos();
		adaptadorUsuario.deleteAll();
		adaptadorMensajes.deleteAll();
		adaptadorContactos.deleteAll();
	}

	@Test
	public void test_1_insertUsers() {
		u1 = new Usuario("Daniel", Calendar.getInstance().getTime(), 666777888, "ataquearcade", "ataquearcade");
		u2 = new Usuario("Salvador", Calendar.getInstance().getTime(), 666777888, "exiled", "exiled");
		adaptadorUsuario.registerUser(u1);
		adaptadorUsuario.registerUser(u2);
		System.out.print("The following users have been added: ");
		for (Usuario u : adaptadorUsuario.getAllUsers())
			System.out.print(u.getName() + " ");
		System.out.println();
	}

	@Test
	public void test_2_logInAsUsers() {
		try {
			if (Controlador.getInstance().login("exiled", "exiled"))
				System.out.println("Salvador has logged in in the system");
			Controlador.getInstance().logOut();
			System.out.println("Salvador has logged out of the system");
			if (Controlador.getInstance().login("ataquearcade", "ataquearcade"))
				System.out.println("Daniel has logged in in the system and will be the current user");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test_3_createContacts() {
		try {
			Controlador.getInstance().addContact(null, u2.getUsername());
			for (Usuario u : adaptadorUsuario.getAllUsers())
				System.out.println(u.getName() + " has added " + u.getContacts().size() + " contacts");
			Controlador.getInstance().setCurrentChat(Controlador.getInstance().getCurrentContacts().get(u2.getName()));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test_4_writeToContacts() {
		try {
			Controlador.getInstance().addMessage("Message added succesfully to contact", 0, null);
			for (Mensaje m : Controlador.getInstance().getCurrentMessages())
				System.out.println(m.getText());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test_5_deleteMessages() {
		try {
			Controlador.getInstance().deleteMessages();
			if (Controlador.getInstance().getCurrentMessages().size() == 0)
				System.out.println("The chat has been reset succesfully");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test_6_createGroup() {
		try {
			List<String> l = new LinkedList<String>();
			l.add(u2.getUsername());
			if (Controlador.getInstance().addContact("groupName", l))
				System.out.println("Group created succesfully");
			Controlador.getInstance().setCurrentChat(Controlador.getInstance().getCurrentContacts().get("groupName"));
			System.out.println("The chat has been set to the new group");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test_6_writeToGroups() {
		try {
			Controlador.getInstance().addMessage("Message added succesfully to group", 0, null);
			for (Mensaje m : Controlador.getInstance().getCurrentMessages())
				System.out.println(m.getText());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test_7_deleteContacts() {
		try {
			if (Controlador.getInstance()
					.deleteGroups(Arrays.asList(Controlador.getInstance().getCurrentContacts().get("groupName"))))
				System.out.println("Group deleted succesfully");
			if (Controlador.getInstance().deleteContact(
					adaptadorContactos.getContact(Controlador.getInstance().getCurrentContacts().get(u2.getName()))))
				System.out.println("Contact Salvador deleted succesfully");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test_8_delete() {
		adaptadorUsuario.deleteAll();
		adaptadorMensajes.deleteAll();
		adaptadorContactos.deleteAll();
		System.out.println("The database has been reset");
	}
}