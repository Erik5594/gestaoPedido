package com.github.erik5594.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import com.github.erik5594.entidades.Cliente;

public class ClienteDao {

	@Inject
	private EntityManager manager;

	public Cliente salvarOrUpdate(Cliente cliente) {
		EntityTransaction entityTransaction = manager.getTransaction();
		entityTransaction.begin();
		cliente = (Cliente) manager.merge(cliente);
		entityTransaction.commit();
		return cliente;
	}

	public Cliente pesquisarById(Long id) {
		return (Cliente) manager.find(Cliente.class, id);
	}
	
	public List<Cliente> listarTodosClientes() {
		return manager.createQuery("from Cliente", Cliente.class)
				.getResultList();
	}
	
	public Cliente pesquisarByCpfCgc(String cpfCgc) {
		try{
			return (Cliente) manager.createQuery("from Cliente where cgcCpf = :cpfCgc", Cliente.class)
					.setParameter("cpfCgc", cpfCgc).getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
}
