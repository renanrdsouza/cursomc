package com.example.cursomc.controller;

import com.example.cursomc.dto.CategoriaDTO;
import com.example.cursomc.model.Categoria;
import com.example.cursomc.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> find(@PathVariable Long id) {
        Categoria categoria = categoriaService.find(id);

        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaService.fromDTO(categoriaDTO);

        categoria = categoriaService.insert(categoria);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Long id) {
        Categoria categoria = categoriaService.fromDTO(categoriaDTO);

        categoria.setId(id);
        categoria = categoriaService.update(categoria);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listAll() {
        List<Categoria> categoria = categoriaService.listAll();
        List<CategoriaDTO> categoriaDTOS = categoria.stream().map(CategoriaDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(categoriaDTOS);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CategoriaDTO>> listPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                           @RequestParam(value = "linhasPorPagina", defaultValue = "24") Integer linhaPorPagina,
                                                           @RequestParam(value = "ordenarPor", defaultValue = "nome") String ordenarPor,
                                                           @RequestParam(value = "direcao", defaultValue = "ASC") String direcao) {
        Page<Categoria> categorias = categoriaService.listPage(page, linhaPorPagina, ordenarPor, direcao);
        Page<CategoriaDTO> categoriaDTOS = categorias.map(CategoriaDTO::new);

        return ResponseEntity.ok().body(categoriaDTOS);
    }
}
