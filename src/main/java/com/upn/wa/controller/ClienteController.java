package com.upn.wa.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.upn.wa.dao.ClienteDAO;
import com.upn.wa.modelo.Cliente;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
	
	@Autowired
	private ClienteDAO clienteDAO;
	
	/*
	 Metodos para hacer las peticiones
	 @GetMapping->Obtener data
	 @PostMapping->Guardar data
	 @PutMapping->Actualizar data
	 @DeleteMapping->Eliminar data
	 */
	
	//Metodo de Listar
	@GetMapping(value="/listar")
	public String listar(Model model)
	{
		model.addAttribute("titulo","Listado de clientes");
		model.addAttribute("clientes",clienteDAO.findAll());
		return "listar";
	}
	//Metodo crear
	@GetMapping(value="/form")
	public String crear(Map<String, Object>model)
	{
		Cliente cliente=new Cliente();
		model.put("cliente",cliente);
		model.put("titulo", "Formular de cliente");
		return "form";
	}
	//Metodo auxiliar al editar
	@GetMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id")Long id, Map<String, Object>model)
	{
		Cliente cliente=null;
		if (id>0) {
			cliente=clienteDAO.findOne(id);
		}
		else
		{
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar cliente");
		return "form";
	}
	
	//Metodo par guardar en BD el cliente
	@PostMapping(value="/form")
	public String guardar(Cliente cliente, BindingResult result, Model model,
			SessionStatus status)
	{
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}
		clienteDAO.save(cliente);
		status.setComplete();
		return "redirect:/listar";
	}
	@GetMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id")Long id)
	{
		if (id>0) {
			clienteDAO.delete(id);
		}
		return "redirect:/listar";
	}
}
