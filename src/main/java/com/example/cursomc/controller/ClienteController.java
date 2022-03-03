package com.example.cursomc.controller;

import com.example.cursomc.dto.CategoriaDTO;
import com.example.cursomc.dto.ClienteDTO;
import com.example.cursomc.dto.ClienteNewDTO;
import com.example.cursomc.model.Categoria;
import com.example.cursomc.model.Cliente;
import com.example.cursomc.model.Cliente;
import com.example.cursomc.service.ClienteService;
import com.example.cursomc.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> list(@PathVariable Long id) {

        Cliente cliente = clienteService.find(id);
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid ClienteNewDTO clienteNewDTO) {
        Cliente cliente = clienteService.fromDTO(clienteNewDTO);

        cliente = clienteService.insert(cliente);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Long id) {
        Cliente cliente = clienteService.fromDTO(clienteDTO);

        cliente.setId(id);

        cliente = clienteService.update(cliente);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listAll() {
        List<Cliente> cliente = clienteService.listAll();
        List<ClienteDTO> clienteDTOS = cliente.stream().map(ClienteDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(clienteDTOS);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ClienteDTO>> listPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "linhasPorPagina", defaultValue = "24") Integer linhaPorPagina,
                                                       @RequestParam(value = "ordenarPor", defaultValue = "nome") String ordenarPor,
                                                       @RequestParam(value = "direcao", defaultValue = "ASC") String direcao) {
        Page<Cliente> Clientes = clienteService.listPage(page, linhaPorPagina, ordenarPor, direcao);
        Page<ClienteDTO> ClienteDTOS = Clientes.map(ClienteDTO::new);

        return ResponseEntity.ok().body(ClienteDTOS);
    }
}
