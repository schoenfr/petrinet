package net.odyne.petrinet.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AuthToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private Date expire;

	private String salt;

	public void setUser(User user) {
		this.user = user;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}

	public Date getExpire() {
		return expire;
	}

	public User getUser() {
		return user;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public boolean isExpired() {
		return this.expire.before(new Date());
	}

}
