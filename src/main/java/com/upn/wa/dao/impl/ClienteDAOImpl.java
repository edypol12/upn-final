package com.upn.wa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.upn.wa.dao.ClienteDAO;
import com.upn.wa.modelo.Cliente;

@Repository
public class ClienteDAOImpl implements ClienteDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	/*
	 En JPA para realizar acciones de CRUD se utiliza los siguientes comandos
	 Insertar datos->persis()
	 Modificar datos->merge()
	 Eliminar datos->renove()
	 Listar datos->getResultList()
	 Buscar por ID->find()
	 */
	
	@Transactional(readOnly=true)
	@Override
	public List<Cliente> findAll() {
		//JPA->trabaja con JPQL
		/*SQLNATIVO->select a.* from cliente a
		JPQL->select a from cliente*/
		
		return em.createQuery("from Cliente").getResultList();
	}

	@Transactional
	@Override
	public void save(Cliente cliente) {
		//Para editar
		if (cliente.getId()!=null && cliente.getId()>0) {
			em.merge(cliente);
		}
		else
		{
			//Para registrar
			em.persist(cliente);
		}
		
	}
	
	@Transactional(readOnly = true)
	@Override
	public Cliente findOne(Long id) {
		return em.find(Cliente.class, id);
	}
	
	@Transactional
	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
		
	}
	
}
