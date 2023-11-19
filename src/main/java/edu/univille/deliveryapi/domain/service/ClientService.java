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
    
    // Injeção de dependência do repositório de clientes
    @Autowired
    private ClientRepository clientRepository;
    
    // Método para salvar um cliente
    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }
    
    // Método para excluir um cliente pelo ID
    @Transactional
    public void delete(Long id) throws Exception {
        // Verifica se o cliente existe antes de tentar excluí-lo
        if (!clientRepository.existsById(id)) {
            throw new Exception("Cliente não encontrado!");
        }
        clientRepository.deleteById(id);
    }
    
    // Método para listar todos os clientes
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
    
    // Método para encontrar um cliente pelo ID
    public Client findById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        
        // Lança uma exceção se o cliente não for encontrado
        return client.orElseThrow(() -> new ObjectNotFoundException("Object not found, Id: " 
		+ id + ", Type: " + Client.class.getName(), client));
    }
    
    // Método para atualizar as informações de um cliente
    public Client update(Long id, Client client) {
        // Encontra o cliente pelo ID
        Client newClient = findById(id);
        
        // Atualiza as informações do cliente
        newClient.setName(client.getName());
        newClient.setEmail(client.getEmail());
        newClient.setPhone(client.getPhone());
        
        // Salva o cliente atualizado no repositório
        return clientRepository.save(newClient);
    }
}
