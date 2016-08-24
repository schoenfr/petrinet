package net.odyne.petrinet.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.odyne.petrinet.entities.AuthToken;

@Repository
public interface AuthTokenRepository extends CrudRepository<AuthToken, Long> {
	
	public AuthToken findBySalt(@Param("salt") String salt);
	
}
