package edu.univille.deliveryapi.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.univille.deliveryapi.domain.model.Client;
import edu.univille.deliveryapi.domain.model.Delivery;
import edu.univille.deliveryapi.domain.model.DeliveryStatus;
import edu.univille.deliveryapi.domain.service.DeliveryRequestService;
import edu.univille.deliveryapi.repository.ClientRepository;
import edu.univille.deliveryapi.repository.DeliveryRepository;

public class DeliveryRequestServiceTest {

    @InjectMocks
    private DeliveryRequestService deliveryRequestService;

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private ClientRepository clientRepository;

    @Test
    public void shouldRequestDelivery() {
        MockitoAnnotations.openMocks(this);

        // Cliente de teste
        Client client = new Client();
        client.setId(1L);
        client.setName("Test");
        client.setEmail("test@test.com");
        client.setPhone("1234567890");

        // Configura o mock do repositório de clientes
        when(clientRepository.findById(1L)).thenReturn(java.util.Optional.of(client));

        // Configuração do mock para o objeto Delivery
        Delivery delivery = new Delivery();
        delivery.setClient(client);
        delivery.setStatus(DeliveryStatus.PENDING);
        delivery.setOrderDate(OffsetDateTime.now());

        // Configura o mock do repositório de entregas
        when(deliveryRepository.save(any(Delivery.class))).thenReturn(delivery);

        // Chamada do método a ser testado
        Delivery requestedDelivery = deliveryRequestService.requestDelivery(delivery);

        // Verificações
        assertEquals(client, requestedDelivery.getClient());
        assertEquals(DeliveryStatus.PENDING, requestedDelivery.getStatus());
    }
}
