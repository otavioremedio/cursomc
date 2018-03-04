package com.otavio.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otavio.cursomc.domain.Categoria;
import com.otavio.cursomc.repositories.CategoriaRepository;
import com.otavio.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id){
		Categoria cat = repo.findOne(id);

		if (cat == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id 
					+ ", Tipo: " + Categoria.class.getName());
		}
		return cat;
	}
}
