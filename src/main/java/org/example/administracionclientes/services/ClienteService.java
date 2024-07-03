package org.example.administracionclientes.services;


import org.example.administracionclientes.dto.ClienteDto;
import org.example.administracionclientes.entities.Cliente;
import org.example.administracionclientes.global.exception.recursoNoEncontrado;
import org.example.administracionclientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }

    public Cliente detallesCliente(String id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new recursoNoEncontrado("No_Found"));
    }

    public List<Cliente> agregarClientes(List<ClienteDto> dtos) {
        List<Cliente> clientes = dtos.stream().map(dto -> {
            Cliente cliente = new Cliente();
            cliente.setNombre(dto.getNombre());
            cliente.setApellido(dto.getApellido());
            cliente.setIdentificacion(dto.getIdentificacion());
            cliente.setTelefono(dto.getTelefono());
            cliente.setEmail(dto.getEmail());
            return cliente;
        }).collect(Collectors.toList());
        return clienteRepository.saveAll(clientes);
    }

    public Cliente actualizarCliente(String id, Cliente cliente) {
        if (clienteRepository.existsById(id)) {
            cliente.setId(id);
            return clienteRepository.save(cliente);
        } else {
            throw new RuntimeException("Cliente no encontrado");
        }
    }

    public Cliente borrarCliente(String id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            clienteRepository.delete(cliente);
            return cliente;
        }
        return null;

    }
}
