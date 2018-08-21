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
import co.edu.usbcali.demo.modelo.TiposDocumentos;
import co.edu.usbcali.demo.test.ClientesTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class ClientesDAOTest {

	private final static Logger log = LoggerFactory.getLogger(ClientesTest.class);

	@Autowired
	private IClientesDAO clientesDAO;

	@Autowired
	private ITiposDocumentosDAO tiposDocumentosDAO;

	private Long cliId = 101818L;

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void aTest() {
		assertNotNull(clientesDAO);
		assertNotNull(tiposDocumentosDAO);

		Clientes clientes = clientesDAO.consultarPorId(cliId);
		assertNull("El cliente ya existe", clientes);

		clientes = new Clientes();
		clientes.setCliDireccion("Avenida Siempre Viva 123");
		clientes.setCliId(cliId);
		clientes.setCliMail("Bsimpson@gmail.com");
		clientes.setCliNombre("Bart Simpson");
		clientes.setCliTelefono("555-5555");

		TiposDocumentos tiposDocumentos = tiposDocumentosDAO.consultarPorId(20L);
		assertNotNull("El tipo de documento no existe", tiposDocumentos);

		clientes.setTiposDocumentos(tiposDocumentos);

		clientesDAO.crear(clientes);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void bTest() {
		assertNotNull(clientesDAO);
		assertNotNull(tiposDocumentosDAO);

		Clientes clientes = clientesDAO.consultarPorId(cliId);
		assertNotNull("El cliente no existe", clientes);

		log.info("" + clientes.getCliId());
		log.info(clientes.getCliNombre());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void cTest() {
		assertNotNull(clientesDAO);
		assertNotNull(tiposDocumentosDAO);

		Clientes clientes = clientesDAO.consultarPorId(cliId);
		assertNotNull("El cliente no existe", clientes);

		clientes.setCliDireccion("Avenida Siempre Viva 123");
		clientes.setCliId(cliId);
		clientes.setCliMail("hsimpson@gmail.com");
		clientes.setCliNombre("Homero J Simpson");
		clientes.setCliTelefono("555-55555");

		TiposDocumentos tiposDocumentos = tiposDocumentosDAO.consultarPorId(20L);
		assertNotNull("El tipo de documento no existe", tiposDocumentos);

		clientes.setTiposDocumentos(tiposDocumentos);

		clientesDAO.modificar(clientes);

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void dTest() {
		assertNotNull(clientesDAO);
		assertNotNull(tiposDocumentosDAO);

		Clientes clientes = clientesDAO.consultarPorId(cliId);
		assertNotNull("El cliente no existe", clientes);

		clientesDAO.borrar(clientes);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void eTest() {
		assertNotNull(clientesDAO);
		assertNotNull(tiposDocumentosDAO);

		List<Clientes> losClientes = clientesDAO.consultar();

		for (Clientes clientes : losClientes) {
			log.info("Id:" + clientes.getCliId());
			log.info("Nombre:" + clientes.getCliNombre());
			log.info("Mail:" + clientes.getCliMail());
			log.info("Tipo documento:" + clientes.getTiposDocumentos().getTdocNombre());
		}
	}

}
