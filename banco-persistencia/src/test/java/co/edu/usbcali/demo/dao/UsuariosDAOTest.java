package co.edu.usbcali.demo.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.Persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.TiposUsuarios;
import co.edu.usbcali.demo.modelo.Usuarios;
import co.edu.usbcali.demo.test.ClientesTest;
import co.edu.usbcali.demo.test.UsuariosTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class UsuariosDAOTest {
	
	private final static Logger log = LoggerFactory.getLogger(UsuariosTest.class);

	@Autowired
	private IUsuariosDAO usuariosDAO;

	@Autowired
	private ITiposUsuariosDAO tiposUsuariosDAO;

	private Long usuCedula=6387124L;

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void aTest() throws Exception {
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
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void bTest() throws Exception {
		assertNotNull(usuariosDAO);
		assertNotNull(tiposUsuariosDAO);

		Usuarios usuarios = usuariosDAO.consultarPorId(usuCedula);
		assertNotNull("El usuario no existe", usuarios);
		
		log.info(""+usuarios.getUsuCedula());
		log.info(usuarios.getUsuNombre()); 
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void cTest() throws Exception {
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
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void dTest() throws Exception {
		assertNotNull(usuariosDAO);
		assertNotNull(tiposUsuariosDAO);

		Usuarios usuarios = usuariosDAO.consultarPorId(usuCedula);
		assertNotNull("El Usuario no existe", usuarios);
		
		usuariosDAO.borrar(usuarios);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void eTest() {
		assertNotNull(usuariosDAO);
		assertNotNull(tiposUsuariosDAO);

		List<Usuarios> losUsuarios = usuariosDAO.consultar();
		
		for (Usuarios usuarios : losUsuarios) {
			log.info("Cedula: "+usuarios.getUsuCedula());
			log.info("Tipo usuario: "+usuarios.getTiposUsuarios().getTusuNombre());
			log.info("Nombre: "+usuarios.getUsuNombre());
			log.info("login: "+usuarios.getUsuLogin());
		}
		
	}
	

}
