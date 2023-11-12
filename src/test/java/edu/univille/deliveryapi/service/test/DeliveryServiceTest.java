package edu.univille.deliveryapi.service.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.univille.deliveryapi.domain.model.Client;
import edu.univille.deliveryapi.domain.model.Delivery;
import edu.univille.deliveryapi.domain.model.DeliveryStatus;
import edu.univille.deliveryapi.domain.service.DeliveryFinalizationService;
import edu.univille.deliveryapi.domain.service.DeliveryRequestService;
import edu.univille.deliveryapi.repository.ClientRepository;
import edu.univille.deliveryapi.repository.DeliveryRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeliveryServiceTest {

    @InjectMocks
    private DeliveryFinalizationService deliveryFinalizationService;
    @InjectMocks
    private DeliveryRequestService deliveryRequestService;

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private ClientRepository clientRepository;

    @Test
    public void shouldRequestDelivery() {
        Delivery delivery = new Delivery();
        Client client = new Client();
        client.setId(1L);
        delivery.setClient(client);

        when(clientRepository.getById(anyLong())).thenReturn(client);
        when(deliveryRepository.save(any(Delivery.class))).thenReturn(delivery);

        Delivery savedDelivery = deliveryRequestService.requestDelivery(delivery);

        assertEquals(DeliveryStatus.PENDING, savedDelivery.getStatus());
        assertNotNull(savedDelivery.getOrderDate());
    }

    @Test
    public void shouldFinalizeDelivery() throws Exception {
        Delivery delivery = new Delivery();
        delivery.setStatus(DeliveryStatus.PENDING);

        when(deliveryRepository.getById(anyLong())).thenReturn(delivery);

        deliveryFinalizationService.finalizeDelivery(1L);

        assertEquals(DeliveryStatus.FINISHED, delivery.getStatus());
        assertNotNull(delivery.getFinalizationDate());
    }
}
