package org.example.administracionclientes.controller;

import org.example.administracionclientes.dto.ClienteDto;
import org.example.administracionclientes.entities.Cliente;
import org.example.administracionclientes.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;
    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerClientes() {
        return ResponseEntity.ok(clienteService.obtenerClientes());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> detallesCliente(@PathVariable("id") String id) {
        Cliente cliente = clienteService.detallesCliente(id);
        return ResponseEntity.ok(cliente);
    }
    @PostMapping
    public ResponseEntity<List<Cliente>> agregarClientes(@RequestBody List<ClienteDto> dtos) {
        List<Cliente> clientes = clienteService.agregarClientes(dtos);
        return ResponseEntity.ok(clientes);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable String id, @RequestBody Cliente cliente) {
        try {
            Cliente actualizado = clienteService.actualizarCliente(id, cliente);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarCliente(@PathVariable("id") String id) {
        Cliente cliente = clienteService.borrarCliente(id);
        if (cliente != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}



