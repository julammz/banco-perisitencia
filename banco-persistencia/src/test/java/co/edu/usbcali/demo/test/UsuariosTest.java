package co.edu.usbcali.demo.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.demo.modelo.TiposUsuarios;
import co.edu.usbcali.demo.modelo.Usuarios;


public class UsuariosTest {

	private final static Logger log=LoggerFactory.getLogger(ClientesTest.class);
	
	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;
	
	private static Long usrId=6387124L;
	
	@Test
	public void aTest() {
		entityManagerFactory = Persistence.createEntityManagerFactory("banco-persistencia");
		entityManager = entityManagerFactory.createEntityManager();
		
			Usuarios usuarios= entityManager.find(Usuarios.class, usrId);
			assertNull("El usuario ya existe", usuarios);
			
			usuarios = new Usuarios();
			usuarios.setUsuCedula(usrId);
			usuarios.setUsuNombre("Julian Martinez");
			usuarios.setUsuLogin("jmartinez");
			usuarios.setUsuClave("56789");
			
			TiposUsuarios tiposUsuarios = entityManager.find(TiposUsuarios.class, 10L);
			assertNotNull("El tipo de usurio no existe", tiposUsuarios);
						
			usuarios.setTiposUsuarios(tiposUsuarios);
			
			entityManager.getTransaction().begin();
				entityManager.persist(usuarios);
			entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void bTest() {
		entityManagerFactory = Persistence.createEntityManagerFactory("banco-persistencia");
		entityManager = entityManagerFactory.createEntityManager();
			
			Usuarios usuarios = entityManager.find(Usuarios.class, usrId);
			assertNotNull("El usuario no existe", usuarios);
					
			log.info(""+usuarios.getUsuCedula());
			log.info(usuarios.getUsuNombre());
			
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
	@Test
	public void cTest() {
		entityManagerFactory = Persistence.createEntityManagerFactory("banco-persistencia");
		entityManager = entityManagerFactory.createEntityManager();
		
			Usuarios usuarios= entityManager.find(Usuarios.class, usrId);
			assertNotNull("El usuario no existe", usuarios);
			
			usuarios = new Usuarios();
			usuarios.setUsuCedula(usrId);
			usuarios.setUsuNombre("Julian Alberto Martinez Martinez");
			usuarios.setUsuLogin("jmartinez");
			usuarios.setUsuClave("56789");
			
			TiposUsuarios tiposUsuarios = entityManager.find(TiposUsuarios.class, 10L);
			assertNotNull("El tipo de usurio no existe", tiposUsuarios);
						
			usuarios.setTiposUsuarios(tiposUsuarios);
			
			entityManager.getTransaction().begin();
				entityManager.merge(usuarios);
			entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
	@Test
	public void dTest() {
		entityManagerFactory=Persistence.createEntityManagerFactory("banco-persistencia");
		entityManager=entityManagerFactory.createEntityManager();
		
			Usuarios usuarios= entityManager.find(Usuarios.class, usrId);
			assertNotNull("El usuario no existe", usuarios);
			
			
			entityManager.getTransaction().begin();
				entityManager.remove(usuarios);
			entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
	@Test
	public void eTest() {
		entityManagerFactory=Persistence.createEntityManagerFactory("banco-persistencia");
		entityManager=entityManagerFactory.createEntityManager();
		
		String jpql="SELECT usr FROM Usuarios usr";
		 
		List<Usuarios> losUsuarios = entityManager.createQuery(jpql).getResultList();
		
		for (Usuarios usuario : losUsuarios) {
			log.info("Cedula:"+usuario.getUsuCedula());
			log.info("Nombre:"+usuario.getUsuNombre());
			log.info("Login:"+usuario.getUsuLogin());
			log.info("Clave:"+usuario.getUsuClave());
			log.info("Tipo Usuario:"+usuario.getTiposUsuarios().getTusuNombre());
		}
		
		entityManager.close();
		entityManagerFactory.close();
	}

}
