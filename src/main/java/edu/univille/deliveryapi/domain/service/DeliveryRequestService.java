package edu.univille.deliveryapi.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.univille.deliveryapi.domain.model.Delivery;
import edu.univille.deliveryapi.domain.model.DeliveryStatus;
import edu.univille.deliveryapi.repository.DeliveryRepository;

@Service
public class DeliveryRequestService {
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Transactional
	public Delivery requestDelivery (Delivery delivery) {
		delivery.setStatus(DeliveryStatus.PENDING);
		delivery.setOrderDate(LocalDateTime.now());
		
		return deliveryRepository.save(delivery);
	}

}
