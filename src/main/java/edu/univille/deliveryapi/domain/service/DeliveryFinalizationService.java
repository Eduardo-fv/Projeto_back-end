package edu.univille.deliveryapi.domain.service;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.univille.deliveryapi.domain.model.Client;
import edu.univille.deliveryapi.domain.model.Delivery;
import edu.univille.deliveryapi.repository.DeliveryRepository;

@Service
public class DeliveryFinalizationService {
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Transactional
	public void finalize (Long deliveryId) {
		Delivery delivery = deliveryRepository.findById(deliveryId);		
		delivery.finalize();
		
		deliveryRepository.save(delivery);
	}

}
