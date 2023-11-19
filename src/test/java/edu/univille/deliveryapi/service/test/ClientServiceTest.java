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
    
    // Instância da classe a ser testada, com mocks injetados automaticamente
    @InjectMocks
    private ClientService clientService;

    // Mock do repositório de clientes, utilizado como dependência pela classe a ser testada
    @Mock
    private ClientRepository clientRepository;

    // Método de configuração executado antes de cada teste
    @BeforeEach
    public void setUp() {
        // Inicializar os mocks usando MockitoAnnotations (evitar o uso de método deprecated)
        MockitoAnnotations.openMocks(this);
    }

    // Teste: Verifica se o método findById retorna o cliente esperado quando encontrado
    @Test
    public void shouldFindById() {
        // Cliente de teste
        Client client = new Client();
        client.setName("Test");
        client.setEmail("test@test.com");
        client.setPhone("1234567890");

        // Configura o mock para retornar o cliente de teste quando chamado com o argumento 1L
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        // Chama o método a ser testado
        Client found = clientService.findById(1L);

        // Assegura que os atributos do cliente retornado são iguais aos atributos do cliente de teste
        assertEquals("Test", found.getName());
        assertEquals("test@test.com", found.getEmail());
        assertEquals("1234567890", found.getPhone());
    }

    // Teste: Verifica se o método findById lança a exceção correta quando nenhum cliente é encontrado
    @Test
    public void shouldThrowFindByIdException() {
        // Configura o mock para retornar um Optional vazio quando chamado com o argumento 1L
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        // Verifica se a exceção ObjectNotFoundException é lançada durante a execução do método
        assertThrows(ObjectNotFoundException.class, () -> {
            clientService.findById(1L);
        });
    }
}
