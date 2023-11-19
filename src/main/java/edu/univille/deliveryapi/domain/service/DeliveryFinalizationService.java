package edu.univille.deliveryapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.univille.deliveryapi.domain.model.Delivery;
import edu.univille.deliveryapi.repository.DeliveryRepository;

@Service
public class DeliveryFinalizationService {
    
    // Injeção de dependência do repositório de entregas
    @Autowired
    private DeliveryRepository deliveryRepository;
    
    // Método para finalizar uma entrega pelo ID
    @Transactional
    public void finalizeDelivery(Long deliveryId) {
        // Utiliza o método findById para encontrar a entrega pelo ID
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("Entrega não encontrada com o ID: " + deliveryId));
        
        // Chama o método finalizeDelivery da classe Delivery para realizar a finalização
        delivery.finalizeDelivery();
        
        // Salva a entrega atualizada no repositório
        deliveryRepository.save(delivery);
    }
}
