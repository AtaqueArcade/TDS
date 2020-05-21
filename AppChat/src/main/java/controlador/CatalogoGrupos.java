package controlador;

import java.util.HashMap;
import java.util.List;
import modelo.Grupo;
import persistencia.DAOusuario;
import persistencia.FactoriaDAO;

public class CatalogoGrupos {
	private FactoriaDAO dao;
	private DAOusuario adaptadorUsuario;
	private HashMap<Integer, Grupo> catalog;
	private static CatalogoGrupos instance;

	public static CatalogoGrupos getInstance()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null) {
			instance = new CatalogoGrupos();
		}
		return instance;
	}

	private CatalogoGrupos() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		dao = FactoriaDAO.getInstance("persistencia.FactoriaDAOImp");
		adaptadorUsuario = dao.getDAOusuario();
		updateAllGroups();
	}

	public boolean addGroup(Grupo group) {
		if (!adaptadorUsuario.registerGroup(group))
			return false;
		return (catalog.put(group.getId(), group) == null);
	}

	public boolean removeGroup(int id) {
		Grupo gr = getGroup(id);
		if (gr != null)
			adaptadorUsuario.deleteGroup(gr);
		return (catalog.remove(id) != null);
	}

	public Grupo getGroup(int id) {
		updateAllGroups();
		return catalog.get(id);
	}

	public void modifyGroup(Grupo group) {
		Grupo modifiedGroup = this.getGroup(group.getId());
		modifiedGroup = group;
		catalog.put(modifiedGroup.getId(), modifiedGroup);
		adaptadorUsuario.modifyGroup(modifiedGroup);
	}

	private void updateAllGroups() {
		this.catalog = new HashMap<Integer, Grupo>();
		List<Grupo> groupList = adaptadorUsuario.getAllGroups();
		for (Grupo group : groupList) {
			catalog.put(group.getId(), group);
		}
	}
}
