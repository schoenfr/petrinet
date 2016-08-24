package net.odyne.petrinet.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.odyne.petrinet.enums.Direction;

@Entity
public class Edge {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Petrinet parent;
	
	@ManyToOne
	@JoinColumn(name = "place_id")
	private Place place;

	@ManyToOne
	@JoinColumn(name = "transition_id")
	private Transition transition;
	
	private Direction direction;
	
	public Petrinet getParent() {
		return parent;
	}

	public void setParent(Petrinet parent) {
		this.parent = parent;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public Place getPlace() {
		return place;
	}
	
	public Transition getTransition() {
		return transition;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public void setPlace(Place place) {
		this.place = place;
	}
	
	public void setTransition(Transition transition) {
		this.transition = transition;
	}
	
}
