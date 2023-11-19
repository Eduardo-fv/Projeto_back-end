package edu.univille.deliveryapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.univille.deliveryapi.domain.model.Client;
import edu.univille.deliveryapi.domain.service.ClientService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
    
    // Injeção de dependência do serviço de clientes
    @Autowired
    private ClientService clientService;
    
    // Endpoint para inserir um novo cliente
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Client insertClient(@Valid @RequestBody Client client) {
        return clientService.save(client);
    }
    
    // Endpoint para deletar um cliente pelo ID
    @DeleteMapping(value = "/{id}")
    public void deleteClientById(@PathVariable Long id) throws Exception {
        clientService.delete(id);
    }
    
    // Endpoint para listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Client>> listClient() {
        List<Client> clients = clientService.findAll();
        
        // Retorna uma resposta com a lista de clientes e status OK (200)
        return ResponseEntity.ok(clients);
    }
    
    // Endpoint para encontrar um cliente pelo ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable Long id) {
        Client client = clientService.findById(id);
        
        // Retorna uma resposta com o cliente encontrado e status OK (200)
        return ResponseEntity.ok(client);
    }
    
    // Endpoint para atualizar um cliente pelo ID
    @PutMapping(value = "/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client) {
        clientService.update(id, client);
        // Retorna uma resposta com o cliente atualizado e status OK (200)
        return ResponseEntity.ok().body(client);
    }
}
