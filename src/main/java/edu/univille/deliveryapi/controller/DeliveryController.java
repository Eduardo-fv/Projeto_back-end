package edu.univille.deliveryapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.univille.deliveryapi.domain.model.Delivery;
import edu.univille.deliveryapi.domain.service.DeliveryFinalizationService;
import edu.univille.deliveryapi.domain.service.DeliveryRequestService;
import edu.univille.deliveryapi.repository.DeliveryRepository;

@RestController
@RequestMapping(value = "/deliveries")
public class DeliveryController {
	
	@Autowired
	private DeliveryRequestService deliveryRequestService;
	@Autowired
	private DeliveryFinalizationService deliveryFinalizationService;
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Delivery requestDelivery (@RequestBody Delivery delivery) {
		return deliveryRequestService.requestDelivery(delivery);
	}
	
	@PutMapping("/{id}/finalizated")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalize (@PathVariable Long deliveryId) throws Exception {
		deliveryFinalizationService.finalizeDelivery(deliveryId);
	}
	
	@GetMapping
	public ResponseEntity<List<Delivery>> getAllDeliveries () {
		List<Delivery> list = deliveryRepository.findAll();
		
		return ResponseEntity.ok(list);
	}

}
