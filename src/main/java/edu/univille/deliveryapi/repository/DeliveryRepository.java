package edu.univille.deliveryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.univille.deliveryapi.domain.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>{

}
