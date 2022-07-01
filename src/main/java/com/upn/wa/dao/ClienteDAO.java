package com.upn.wa.dao;

import java.util.List;

import com.upn.wa.modelo.Cliente;

public interface ClienteDAO {
	
	//Metodo para obtener todos los clientes y los devuelve en una lista
	public List<Cliente> findAll();
	//Metodo para guardar clientes, no necesito que retorne nada
	public void save(Cliente cliente);
	//Metodo para buscarporID y devuelva un objeto cliente
	public Cliente findOne(Long id);
	//Metodo para eliminar clientes y no necesito que retorne nada
	public void delete(Long id);	
	
}
