package edu.univille.deliveryapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.univille.deliveryapi.domain.model.Client;
import edu.univille.deliveryapi.domain.service.ClientService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Client insertClient (@Valid @RequestBody Client client) {
		return clientService.save(client);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteClientById (@PathVariable Long id) throws Exception {
		clientService.delete(id);
	}
	
	@GetMapping
	public ResponseEntity<List<Client>> listClient () {
		List<Client> clients = clientService.findAll();
		
		return ResponseEntity.ok(clients);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> findClientById (@PathVariable Long id) {
		Client client = clientService.findById(id);
		
		return ResponseEntity.ok(client);
	}

}
