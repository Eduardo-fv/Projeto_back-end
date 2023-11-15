package edu.univille.deliveryapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Delivery requestDelivery(@RequestBody Delivery delivery) {
        return deliveryRequestService.requestDelivery(delivery);
    }

    @PutMapping("/{id}/finalizated")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalize(@PathVariable Long deliveryId) throws Exception {
        deliveryFinalizationService.finalizeDelivery(deliveryId);
    }

    @GetMapping
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        List<Delivery> list = deliveryRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable Long id) {
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        return delivery.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

	@PutMapping("/{id}")
	public ResponseEntity<Delivery> updateDelivery(@PathVariable Long id, @RequestBody Delivery updatedDelivery) {
		Optional<Delivery> existingDeliveryOptional = deliveryRepository.findById(id);

		if (existingDeliveryOptional.isPresent()) {
			Delivery existingDelivery = existingDeliveryOptional.get();

			// Atualiza os campos necessários
			existingDelivery.setTax(updatedDelivery.getTax());

			// Salva a entrega atualizada
			Delivery savedDelivery = deliveryRepository.save(existingDelivery);

			return ResponseEntity.ok(savedDelivery);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDelivery(@PathVariable Long id) {
		Optional<Delivery> existingDeliveryOptional = deliveryRepository.findById(id);

		if (existingDeliveryOptional.isPresent()) {
			deliveryRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}
