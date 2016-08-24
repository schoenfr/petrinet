package net.odyne.petrinet.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String username;
	private String password;

	@Column(nullable = false, unique = true)
	private String email;
	
	@OneToMany(targetEntity=Petrinet.class, mappedBy = "owner", cascade = CascadeType.ALL)
	private Set<Petrinet> petrinets;
	
	public UserDetails toUserDetails() {
		return new org.springframework.security.core.userdetails.User(username, password, null);
	}
	
	public Set<Petrinet> getPetrinets() {
		return petrinets;
	}
	
	public void setPetrinets(Set<Petrinet> petrinets) {
		this.petrinets = petrinets;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	
}