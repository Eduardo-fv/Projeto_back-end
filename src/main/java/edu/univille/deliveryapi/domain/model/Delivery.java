package edu.univille.deliveryapi.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_delivery")
public class Delivery {
	
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Valid
	@NotNull
	@ManyToOne
	private Client client;
	
	@Embedded
	private Receiver receiver;
	
	@NotNull
	private BigDecimal tax;
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime orderDate;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime finalizationDate;
	
	public Long getId() 
	{ return id; }
	
	public Client getClient() 
	{ return client; }
	
	public Receiver getReceiver() 
	{ return receiver; }
	
	public BigDecimal getTax() 
	{ return tax; }
	
	public OffsetDateTime getOrderDate()
	{ return orderDate; }
	
	public OffsetDateTime getFinalizationDate()
	{ return finalizationDate; }
	
	public DeliveryStatus getStatus() 
	{ return status; }
	
	public void setClient(Client client) 
	{ this.client = client; }
	
	public void setReceiver(Receiver receiver) 
	{ this.receiver = receiver; }
	
	public void setTax(BigDecimal tax) 
	{ this.tax = tax; }
	
	public void setStatus(DeliveryStatus status) 
	{ this.status = status; }
	
	public void setOrderDate(OffsetDateTime orderDate) 
	{ this.orderDate = orderDate; }
	
	public void setFinalizationDate(OffsetDateTime finalizationDate) 
	{ this.finalizationDate = finalizationDate; }
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Delivery other = (Delivery) obj;
		return Objects.equals(id, other.id);
	}
	
	public void finalizeDelivery() {
		if (!DeliveryStatus.PENDING.equals(getStatus())) {
			throw new IllegalStateException("Delivery cannot be finished.");
		} else {
			setStatus(DeliveryStatus.FINISHED);
			setFinalizationDate(OffsetDateTime.now());
		}
	}
}
