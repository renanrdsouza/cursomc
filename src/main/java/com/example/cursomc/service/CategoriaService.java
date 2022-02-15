package com.example.cursomc.service;

import com.example.cursomc.model.Categoria;
import com.example.cursomc.repository.CategoriaRepository;
import com.example.cursomc.service.exceptions.DataIntegrityException;
import com.example.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void deletar(Long id) {
        try{
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
        }
    }

    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }

    public Page<Categoria> listarPagina(Integer page, Integer linhaPorPagina, String ordenarPor, String direcao) {
        PageRequest pageRequest = PageRequest.of(page, linhaPorPagina, Sort.Direction.valueOf(direcao), ordenarPor);
        return categoriaRepository.findAll(pageRequest);
    }
}
