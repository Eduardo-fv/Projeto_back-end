package edu.univille.deliveryapi.domain.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.univille.deliveryapi.domain.model.Client;
import edu.univille.deliveryapi.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional
	public Client save (Client client) {
		return clientRepository.save(client);
	}
	
	@Transactional
	public void delete (Long id) throws Exception{
		if (!clientRepository.existsById(id)) {
			throw new Exception("Client not found!");
		}
		clientRepository.deleteById(id);
	}
	
	public List<Client> findAll() {
		return clientRepository.findAll();
	}
	
	public Client findById(Long id) {
		Optional<Client> client = clientRepository.findById(id);
		return client.orElseThrow(() -> new ObjectNotFoundException("Object not found, Id: " 
		+ id + ", Type: " + Client.class.getName(), client));
	}
	
	public Client update (Long id, Client client) {
		Client newClient = findById(id);
		newClient.setName(client.getName());
		newClient.setEmail(client.getEmail());
		newClient.setPhone(client.getPhone());
		return clientRepository.save(newClient);
	}

}
