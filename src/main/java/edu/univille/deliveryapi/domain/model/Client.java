package edu.univille.deliveryapi.domain.model;

import java.util.Objects;

import edu.univille.deliveryapi.domain.ValidationGroups;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;

@Entity
@Table(name = "tb_client")
public class Client {
	
	@NotNull(groups = ValidationGroups.ClientId.class)
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClientId.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 60)
	private String name;
	
	@NotBlank
	@Email
	@Size(max = 255)
	private String email;
	
	@NotBlank
	@Size(max = 20)
	private String phone;
	
	public Long getId() 
	{ return id; }
	public String getName() 
	{ return name; }
	public String getEmail() 
	{ return email; }
	public String getPhone() 
	{ return phone; }
	
	public void setId(Long id)
	{ this.id = id; }
	public void setName(String name) 
	{ this.name = name; }
	public void setEmail(String email) 
	{ this.email = email; }
	public void setPhone(String phone) 
	{ this.phone = phone; }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	}

}
