package edu.univille.deliveryapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_delivery")
public class Delivery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Client client;
	@Embedded
	private Receiver receiver;
	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;
	
	private BigDecimal tax;
	private LocalDateTime orderDate;
	private LocalDateTime finalizationDate;
	
	public Long getId() 
	{ return id; }
	public Client getClient() 
	{ return client; }
	public Receiver getReceiver() 
	{ return receiver; }
	public BigDecimal getTax() 
	{ return tax; }
	public LocalDateTime getOrderDate()
	{ return orderDate; }
	public LocalDateTime getFinalizationDate()
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
	public void setOrderDate(LocalDateTime orderDate) 
	{ this.orderDate = orderDate; }
	public void setFinalizationDate(LocalDateTime finalizationDate) 
	{ this.finalizationDate = finalizationDate; }
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
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
	
}
