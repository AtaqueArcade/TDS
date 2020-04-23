package persistencia;

public class FactoriaDAOImp extends FactoriaDAO{
		public FactoriaDAOImp(){}

		public DAOusuario getDAOusuario() {
			return AdaptadorUsuario.getInstance();
		}
}
