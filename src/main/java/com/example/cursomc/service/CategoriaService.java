package com.example.cursomc.service;

import com.example.cursomc.model.Categoria;
import com.example.cursomc.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public Categoria listar(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElse(null);
    }

    public void criar(Categoria categoria) {
        categoriaRepository.save(categoria);
    }
}
