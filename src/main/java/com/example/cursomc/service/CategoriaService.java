package com.example.cursomc.service;

import com.example.cursomc.model.Categoria;
import com.example.cursomc.repository.CategoriaRepository;
import com.example.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Categoria criar(Categoria categoria) {
        categoria.setId(null); // garante que o método criar está inserindo algo novo
        // caso o id não fosse nulo o método save iria como uma atualização e não uma criação
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizar(Categoria categoria) {
        listar(categoria.getId());
        return categoriaRepository.save(categoria);
    }
}
