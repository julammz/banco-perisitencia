package co.edu.usbcali.demo.logica;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import co.edu.usbcali.demo.dao.ITiposUsuariosDAO;
import co.edu.usbcali.demo.modelo.TiposUsuarios;
import co.edu.usbcali.demo.modelo.Usuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class UsuariosLogicaTest {


	
	private final static Logger log=LoggerFactory.getLogger(UsuariosLogicaTest.class);
	
	@Autowired
	private IUsuariosLogica usuariosDAO;
	
	@Autowired
	private ITiposUsuariosDAO tiposUsuariosDAO;	
	
	private Long usuCedula=6387124L;

	@Test
	public void atest()throws Exception {
		assertNotNull(usuariosDAO);
		assertNotNull(tiposUsuariosDAO);

		Usuarios usuarios = usuariosDAO.consultarPorId(usuCedula);
		assertNull("El Usuario ya existe", usuarios);
		
		usuarios = new Usuarios();
		usuarios.setUsuCedula(usuCedula);
		
		TiposUsuarios tiposUsuarios = tiposUsuariosDAO.consultarPorId(10L);
		assertNotNull("El tipo de usuario no existe", tiposUsuarios);
		
		usuarios.setTiposUsuarios(tiposUsuarios);
		
		usuarios.setUsuNombre("Julian Alberto Martinez Martinez");
		usuarios.setUsuLogin("jamartinez");
		usuarios.setUsuClave("102938");
		
		usuariosDAO.crear(usuarios);
	}
	
	
	@Test
	public void btest()throws Exception {
		assertNotNull(usuariosDAO);
		assertNotNull(tiposUsuariosDAO);
		
		Usuarios usuarios = usuariosDAO.consultarPorId(usuCedula);
		assertNotNull("El usuario no existe", usuarios);
		
		log.info("Nombre:"+usuarios.getUsuNombre());			
	}
	
	@Test
	public void ctest() throws Exception{
		assertNotNull(usuariosDAO);
		assertNotNull(tiposUsuariosDAO);

		Usuarios usuarios = usuariosDAO.consultarPorId(usuCedula);
		assertNotNull("El Usuario no existe", usuarios);
		
		usuarios = new Usuarios();
		usuarios.setUsuCedula(usuCedula);
		
		TiposUsuarios tiposUsuarios = tiposUsuariosDAO.consultarPorId(10L);
		assertNotNull("El tipo de usuario no existe", tiposUsuarios);
		
		usuarios.setTiposUsuarios(tiposUsuarios);
		
		usuarios.setUsuNombre("Julian A. Martinez M");
		usuarios.setUsuLogin("jamartinezmz");
		usuarios.setUsuClave("123456");
		
		usuariosDAO.modificar(usuarios);
	}
	
	@Test
	public void dtest()throws Exception {
		assertNotNull(usuariosDAO);
		assertNotNull(tiposUsuariosDAO);

		Usuarios usuarios = usuariosDAO.consultarPorId(usuCedula);
		assertNotNull("El Usuario no existe", usuarios);
		
		usuariosDAO.borrar(usuarios);
	}
	
	@Test	
	public void etest() {
		assertNotNull(usuariosDAO);
		assertNotNull(tiposUsuariosDAO);

		List<Usuarios> losUsuarios = usuariosDAO.consultar();
		
		for (Usuarios usuarios : losUsuarios) {
			log.info("Cedula: "+usuarios.getUsuCedula());
			//TODO ahcer esta aosciacion log.info("Tipo usuario: "+ usuarios.getTiposUsuarios().getTusuNombre());
			log.info("Nombre: "+usuarios.getUsuNombre());
			log.info("login: "+usuarios.getUsuLogin());
		}	
	}

}
