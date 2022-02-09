package com.example.cursomc.repository;

import com.example.cursomc.model.Endereco;
import com.example.cursomc.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
