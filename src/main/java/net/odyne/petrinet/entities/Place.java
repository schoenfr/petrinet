package net.odyne.petrinet.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Place extends AbstractNode {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Petrinet parent;
	
	public Petrinet getParent() {
		return parent;
	}

	public void setParent(Petrinet parent) {
		this.parent = parent;
	}
	
}
