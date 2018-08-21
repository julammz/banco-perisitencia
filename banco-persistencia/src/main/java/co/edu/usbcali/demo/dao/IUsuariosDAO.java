package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.Usuarios;

public interface IUsuariosDAO {
	
	public void crear(Usuarios entity);
	public void modificar(Usuarios entity);
	public void borrar(Usuarios entity);
	public Usuarios consultarPorId (Long usuCedula);
	public List<Usuarios> consultar();


}
