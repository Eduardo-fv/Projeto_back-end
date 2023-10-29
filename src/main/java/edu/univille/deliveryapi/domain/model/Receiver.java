package edu.univille.deliveryapi.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Receiver {
	
	@Column(name = "receiver_name")
	private String name;
	
	@Column(name = "receiver_address")
	private String address;
	
	@Column(name = "receiver_number")
	private String number;
	
	@Column(name = "receiver_complement")
	private String complement;
	
	@Column(name = "receiver_district")
	private String district;
	
	public String getName() 
	{return name;}
	public String getAddress() 
	{ return address; }
	public String getNumber() 
	{ return number; }
	public String getComplement() 
	{ return complement; }
	public String getDistrict() 
	{ return district; }
	
	public void setName(String name) 
	{ this.name = name; }
	public void setAddress(String address) 
	{ this.address = address; }
	public void setNumber(String number) 
	{ this.number = number; }
	public void setComplement(String complement) 
	{ this.complement = complement; }
	public void setDistrict(String district) 
	{ this.district = district; }

}
