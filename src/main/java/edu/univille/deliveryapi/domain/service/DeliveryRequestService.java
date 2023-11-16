package edu.univille.deliveryapi.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.univille.deliveryapi.domain.model.Client;
import edu.univille.deliveryapi.domain.model.Delivery;
import edu.univille.deliveryapi.domain.model.DeliveryStatus;
import edu.univille.deliveryapi.repository.ClientRepository;
import edu.univille.deliveryapi.repository.DeliveryRepository;

@Service
public class DeliveryRequestService {
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional
	public Delivery requestDelivery (Delivery delivery) {
		Client client = clientRepository.getById(delivery.getClient().getId());
		
		delivery.setStatus(DeliveryStatus.PENDING);
		delivery.setOrderDate(OffsetDateTime.now());
		
		return deliveryRepository.save(delivery);
	}

}
