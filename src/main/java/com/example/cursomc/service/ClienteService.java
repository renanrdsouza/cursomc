package com.example.cursomc.service;

import com.example.cursomc.model.Categoria;
import com.example.cursomc.model.Cliente;
import com.example.cursomc.repository.ClienteRepository;
import com.example.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente listar(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", " +
                "Tipo: " + Categoria.class.getName()));
    }

    public Cliente criar(@RequestBody Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }
}
