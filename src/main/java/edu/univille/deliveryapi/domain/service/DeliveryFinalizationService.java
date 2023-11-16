package edu.univille.deliveryapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.univille.deliveryapi.domain.model.Delivery;
import edu.univille.deliveryapi.repository.DeliveryRepository;

@Service
public class DeliveryFinalizationService {
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Transactional
	public void finalizeDelivery (Long deliveryId) throws Exception {
		Delivery delivery = deliveryRepository.getById(deliveryId);		
		delivery.finalizeDelivery();
		
		deliveryRepository.save(delivery);
	}

}
