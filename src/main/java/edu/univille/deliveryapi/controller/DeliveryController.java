package edu.univille.deliveryapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.univille.deliveryapi.domain.model.Delivery;
import edu.univille.deliveryapi.domain.service.DeliveryRequestService;

@RestController
@RequestMapping(value = "/deliveries")
public class DeliveryController {
	
	@Autowired
	private DeliveryRequestService deliveryRequestService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Delivery requestDelivery (@RequestBody Delivery delivery) {
		return deliveryRequestService.requestDelivery(delivery);
	}

}
