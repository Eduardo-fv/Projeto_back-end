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

    // Injeção de dependências dos serviços e repositório necessários
    @Autowired
    private DeliveryRequestService deliveryRequestService;
    @Autowired
    private DeliveryFinalizationService deliveryFinalizationService;
    @Autowired
    private DeliveryRepository deliveryRepository;

    // Endpoint para solicitar uma nova entrega
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Delivery requestDelivery(@RequestBody Delivery delivery) {
        return deliveryRequestService.requestDelivery(delivery);
    }

    // Endpoint para finalizar uma entrega pelo ID
    @PutMapping("/{id}/finalizated")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalize(@PathVariable("id") Long deliveryId) throws Exception {
        deliveryFinalizationService.finalizeDelivery(deliveryId);
    }

    // Endpoint para obter todas as entregas
    @GetMapping
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        List<Delivery> list = deliveryRepository.findAll();
        return ResponseEntity.ok(list);
    }

    // Endpoint para obter uma entrega pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable Long id) {
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        return delivery.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para atualizar uma entrega pelo ID
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

    // Endpoint para excluir uma entrega pelo ID
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
