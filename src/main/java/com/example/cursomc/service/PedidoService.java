package com.example.cursomc.service;

import com.example.cursomc.model.Categoria;
import com.example.cursomc.model.Pedido;
import com.example.cursomc.repository.CategoriaRepository;
import com.example.cursomc.repository.PedidoRepository;
import com.example.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public Pedido listar(Long id) {
        Optional<Pedido> categoria = pedidoRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", " +
                "Tipo: " + Categoria.class.getName()));
    }

    public Pedido criar(@RequestBody Pedido pedido) {
        pedidoRepository.save(pedido);
        return pedido;
    }
}
