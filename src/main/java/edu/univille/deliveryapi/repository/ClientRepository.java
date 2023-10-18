package edu.univille.deliveryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.univille.deliveryapi.domain.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {	

}
