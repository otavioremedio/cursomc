package com.otavio.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otavio.cursomc.domain.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
