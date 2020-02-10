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
	/*
		ServicioPersistencia servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia(); 
	  	servPersistencia.recuperarEntidades(); 
	 	Entidad usuario= new Entidad();
		usuario.setNombre("Usuario");
		usuario.setPropiedades(new
		ArrayList<Propiedad>(Arrays.asList(
		new Propiedad("nif", "12345678A"))));
		servPersistencia.registrarEntidad(usuario); 
		
		//Registrar objeto prod1 de tipo Producto
		Entidad eProducto = new Entidad(); 
		eProducto.setNombre("Producto");
		eProducto.setPropiedades(newArrayList<Propiedad>(Arrays.asList(
		new Propiedad("nombre", prod1.getNombre()),
		new Propiedad("descripcion", prod1.getDescripcion()),
		new Propiedad("precio", prod1.getPrecio())))));
		eProducto = servPersistencia.registrarEntidad(eProducto);
		//el id de la Entidad se autogenera
		 idProducto = eProducto.getId());
		//se asigna al objeto prod1
		prod1.setCodigo(idProducto);
		
		...
	 */
}
