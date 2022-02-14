package com.example.cursomc.controller;

import com.example.cursomc.model.Categoria;
import com.example.cursomc.model.Cliente;
import com.example.cursomc.service.CategoriaService;
import com.example.cursomc.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listar(@PathVariable Long id) {

        Cliente cliente = clienteService.listar(id);
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping
    public Cliente criar(@RequestBody Cliente cliente) {
        clienteService.criar(cliente);
        return cliente;
    }
}
