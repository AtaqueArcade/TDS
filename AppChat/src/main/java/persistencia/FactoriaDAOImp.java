package persistencia;

public class FactoriaDAOImp extends FactoriaDAO {
	public FactoriaDAOImp() {
	}

	@Override
	public DAOusuario getDAOusuario() {
		return AdaptadorUsuario.getInstance();
	}

	@Override
	public DAOmensajes getDAOmensajes() {
		return AdaptadorMensajes.getInstance();
	}

	@Override
	public DAOcontacto getDAOcontactos() {
		return AdaptadorContacto.getInstance();
	}
}
