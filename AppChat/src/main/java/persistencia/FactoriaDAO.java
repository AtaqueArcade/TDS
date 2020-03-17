package persistencia;

public class FactoriaDAO {
	private static FactoriaDAO instance;

	public static FactoriaDAO getInstance() {
		if (instance == null) {
			instance = new FactoriaDAO();
		}
		return instance;
	}

	protected FactoriaDAO() {
	}

	public DAOusuario getDAOusuario() {
		return AdaptadorUsuario.getInstance();
	}
}
