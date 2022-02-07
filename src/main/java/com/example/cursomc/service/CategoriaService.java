package com.example.cursomc.service;

import com.example.cursomc.model.Categoria;
import com.example.cursomc.repository.CategoriaRepository;
import com.example.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public Categoria listar(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", " +
                "Tipo: " + Categoria.class.getName()));
    }

    public Categoria criar(@RequestBody Categoria categoria) {
        categoriaRepository.save(categoria);
        return categoria;
    }
}
