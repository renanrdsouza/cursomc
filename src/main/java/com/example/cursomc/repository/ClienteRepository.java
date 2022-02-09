package com.example.cursomc.repository;

import com.example.cursomc.model.Cliente;
import com.example.cursomc.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
