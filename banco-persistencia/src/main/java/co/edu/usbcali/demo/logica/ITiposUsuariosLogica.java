package co.edu.usbcali.demo.logica;

import java.util.List;

import co.edu.usbcali.demo.modelo.TiposUsuarios;

public interface ITiposUsuariosLogica {
	
	public TiposUsuarios consultarPorId(long tipoUsuario);
	public List<TiposUsuarios> consultar();	

}
