package persistencia;


public abstract class FactoriaDAO {
	private static FactoriaDAO instance;
	
	public static FactoriaDAO getInstance(String type) throws InstantiationException, IllegalAccessException, ClassNotFoundException{ //Exception?
		if(instance == null)
			instance = (FactoriaDAO) Class.forName(type).newInstance();
		return instance;
	} 
	
	public static FactoriaDAO getInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		if(instance == null) return getInstance ("persistencia.FactoriaDAOImp");
		else return instance;
	}
	
	public abstract DAOusuario getDAOusuario();
	
	/*
	public DAOusuario getDAOusuario() {
		return AdaptadorUsuario.getInstance();
	}
	*/
}
