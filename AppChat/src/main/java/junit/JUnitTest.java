package junit;

import java.util.Calendar;
import java.util.LinkedList;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import modelo.Grupo;
import modelo.Mensaje;
import modelo.Usuario;
import persistencia.DAOmensajes;
import persistencia.DAOusuario;
import persistencia.FactoriaDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class JUnitTest {

	private static FactoriaDAO dao;
	private static DAOusuario adaptadorUsuario;
	private static DAOmensajes adaptadorMensajes;
	private static Usuario u1;
	private static Usuario u2;
	private static int msgId;
	private static Mensaje message;
	private static LinkedList<Mensaje> list;

	@BeforeClass
	public static void initialize() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		dao = FactoriaDAO.getInstance();
		adaptadorUsuario = dao.getDAOusuario();
		adaptadorMensajes = dao.getDAOmensajes();
		adaptadorUsuario.deleteAll();
		adaptadorMensajes.deleteAll();
	}

	@Test
	public void test_1_insertUsersTest() {
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
	public void test_2_createContacts() {
		msgId = adaptadorMensajes.createMessageList();
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.add(u2.getId());
		u1.addContact(adaptadorUsuario.getAsContacts(l).get(0), msgId);
		adaptadorUsuario.modifyUser(u1);
		l.clear();
		l.add(u1.getId());
		u2.addContact(adaptadorUsuario.getAsContacts(l).get(0), msgId);
		adaptadorUsuario.modifyUser(u2);
		for (Usuario u : adaptadorUsuario.getAllUsers())
			System.out.println(u.getName() + " has added " + u.getContacts().size() + " contacts");
	}

	@Test
	public void test_3_writeToContacts() {
		message = new Mensaje("Message added succesfully to contact", 0, u1.getName());
		list = adaptadorMensajes.getMessageList(u1.getMessages(u1.getContacts().get(0)));
		list.add(message);
		adaptadorMensajes.modifyMessageList(msgId, list);
		for (Mensaje m : adaptadorMensajes.getMessageList(msgId))
			System.out.println(m.getText());
	}

	@Test
	public void test_4_createGroup() {
		Grupo group = new Grupo("Test group", u1.getId(), u1.getContacts());
		adaptadorUsuario.registerGroup(group);
		msgId = adaptadorMensajes.createMessageList();
		u1.addContact(group, msgId);
		adaptadorUsuario.modifyUser(u1);
		u2.addContact(group, msgId);
		adaptadorUsuario.modifyUser(u2);
		System.out.println("Group '" + adaptadorUsuario.getAllGroups().get(0).getName() + "' created succesfully");
	}

	@Test
	public void test_5_writeToGroups() {
		message = new Mensaje("Message added succesfully to group", 0, u1.getName());
		list = adaptadorMensajes.getMessageList(u1.getMessages(u1.getContacts().get(1)));
		list.add(message);
		adaptadorMensajes.modifyMessageList(msgId, list);
		for (Mensaje m : adaptadorMensajes.getMessageList(msgId))
			System.out.println(m.getText());
	}

	@Test
	public void test_6_delete() {
		adaptadorUsuario.deleteAll();
		adaptadorMensajes.deleteAll();
		System.out.println("The database has been reset");
	}
}