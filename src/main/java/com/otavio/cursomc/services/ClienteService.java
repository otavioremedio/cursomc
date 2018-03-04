package com.otavio.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otavio.cursomc.domain.Cliente;
import com.otavio.cursomc.repositories.ClienteRepository;
import com.otavio.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id){
		Cliente cat = repo.findOne(id);

		if (cat == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id 
					+ ", Tipo: " + Cliente.class.getName());
		}
		return cat;
	}
}
