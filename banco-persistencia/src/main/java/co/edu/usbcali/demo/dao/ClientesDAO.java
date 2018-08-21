package co.edu.usbcali.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.Clientes;


@Repository
@Scope("singleton")
public class ClientesDAO implements IClientesDAO{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void crear(Clientes entity) {
		entityManager.persist(entity);
		
	}

	@Override
	public void modificar(Clientes entity) {
		entityManager.merge(entity);
		
	}

	@Override
	public void borrar(Clientes entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public Clientes consultarPorId(Long cliId) {
		return entityManager.find(Clientes.class, cliId);
	}

	@Override
	public List<Clientes> consultar() {
		String jpql="SELECT cli From Clientes cli";
		return entityManager.createQuery(jpql).getResultList();
	}

}
