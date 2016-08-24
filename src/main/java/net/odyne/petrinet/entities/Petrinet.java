package net.odyne.petrinet.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Petrinet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;
	
	@OneToMany(targetEntity=Place.class, mappedBy = "parent", cascade = CascadeType.ALL)
	private Set<Place> places;

	@OneToMany(targetEntity=Transition.class, mappedBy = "parent", cascade = CascadeType.ALL)
	private Set<Transition> transitions;

	@OneToMany(targetEntity=Edge.class, mappedBy = "parent", cascade = CascadeType.ALL)
	private Set<Edge> edges;

	public Set<Place> getPlaces() {
		return places;
	}
	
	public void setPlaces(Set<Place> places) {
		this.places = places;
	}

	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public void setTransitions(Set<Transition> transitions) {
		this.transitions = transitions;
	}
	
	public Set<Transition> getTransitions() {
		return transitions;
	}
	
}
