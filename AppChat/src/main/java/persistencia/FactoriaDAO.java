package persistencia;

import TDS.AppChat.AppChat_Constants;

public abstract class FactoriaDAO {
	private static FactoriaDAO instance;

	public static FactoriaDAO getInstance(String type)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null)
			instance = (FactoriaDAO) Class.forName(type).newInstance();
		return instance;
	}

	public static FactoriaDAO getInstance()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null)
			return getInstance(AppChat_Constants.DAO);
		else
			return instance;
	}

	public abstract DAOusuario getDAOusuario();

	public abstract DAOmensajes getDAOmensajes();

	public abstract DAOcontacto getDAOcontactos();
}
