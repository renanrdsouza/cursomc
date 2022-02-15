package com.example.cursomc.controller;

import com.example.cursomc.model.Categoria;
import com.example.cursomc.model.Pedido;
import com.example.cursomc.service.CategoriaService;
import com.example.cursomc.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> listar(@PathVariable Long id) {

        Pedido pedido = pedidoService.listar(id);
        return ResponseEntity.ok().body(pedido);
    }

    @PostMapping
    public Pedido criar(@RequestBody Pedido pedido) {
        pedidoService.criar(pedido);
        return pedido;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        List<Pedido> pedidos = pedidoService.listarTodos();
        return ResponseEntity.ok().body(pedidos);
    }
}
