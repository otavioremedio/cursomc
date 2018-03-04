package com.otavio.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otavio.cursomc.domain.Cidade;
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
