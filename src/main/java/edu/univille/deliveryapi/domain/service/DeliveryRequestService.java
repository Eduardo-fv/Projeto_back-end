package edu.univille.deliveryapi.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.univille.deliveryapi.domain.model.Delivery;
import edu.univille.deliveryapi.domain.model.DeliveryStatus;
import edu.univille.deliveryapi.repository.ClientRepository;
import edu.univille.deliveryapi.repository.DeliveryRepository;

@Service
public class DeliveryRequestService {
    
    // Injeção de dependências dos repositórios necessários
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private ClientRepository clientRepository;
    
    // Método para solicitar uma nova entrega
    @Transactional
    public Delivery requestDelivery(Delivery delivery) {
        // Verifica se o cliente associado à entrega existe no repositório
        clientRepository.findById(delivery.getClient().getId()).orElse(null);

        // Define o status da entrega como PENDENTE e atribui a data do pedido
        delivery.setStatus(DeliveryStatus.PENDING);
        delivery.setOrderDate(OffsetDateTime.now());
        
        // Salva a entrega no repositório
        return deliveryRepository.save(delivery);
    }
}
