package com.example.cursomc.service;

import com.example.cursomc.dto.ClienteDTO;
import com.example.cursomc.model.Cliente;
import com.example.cursomc.repository.ClienteRepository;
import com.example.cursomc.service.exceptions.DataIntegrityException;
import com.example.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente find(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", " +
                "Tipo: " + Cliente.class.getName()));
    }

    public Cliente insert(@RequestBody Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }

    public Cliente update(Cliente cliente) {
        Cliente clienteAtualizado = find(cliente.getId());
        updateData(clienteAtualizado, cliente);
        return clienteRepository.save(clienteAtualizado);
    }

    public void delete(Long id) {
        try{
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma cliente porque há entidades relacionadas.");
        }
    }

    public List<Cliente> listAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> listPage(Integer page, Integer linhaPorPagina, String ordenarPor, String direcao) {
        PageRequest pageRequest = PageRequest.of(page, linhaPorPagina, Sort.Direction.valueOf(direcao), ordenarPor);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDTO) {
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
    }

    private void updateData(Cliente clienteAtualizado, Cliente cliente) {
        clienteAtualizado.setNome(cliente.getNome());
        clienteAtualizado.setEmail(cliente.getEmail());
    }
}
