package com.example.cursomc.controller;

import com.example.cursomc.model.Categoria;
import com.example.cursomc.repository.CategoriaRepository;
import com.example.cursomc.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> listar(@PathVariable Long id) {

        Categoria categoria = categoriaService.listar(id);
        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping
    public Categoria criar(@RequestBody Categoria categoria) {
        categoriaService.criar(categoria);
        return categoria;
    }
}
