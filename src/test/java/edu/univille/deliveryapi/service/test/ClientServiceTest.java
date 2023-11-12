package edu.univille.deliveryapi.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.univille.deliveryapi.domain.model.Client;
import edu.univille.deliveryapi.domain.service.ClientService;
import edu.univille.deliveryapi.repository.ClientRepository;

public class ClientServiceTest {
	
    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindById() {
        Client client = new Client();
        client.setName("Test");
        client.setEmail("test@test.com");
        client.setPhone("1234567890");

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Client found = clientService.findById(1L);

        assertEquals("Test", found.getName());
        assertEquals("test@test.com", found.getEmail());
        assertEquals("1234567890", found.getPhone());
    }

    @Test
    public void shouldThrowFindByIdException() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> {
            clientService.findById(1L);
        });
    }
}