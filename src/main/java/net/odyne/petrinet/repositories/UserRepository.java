package net.odyne.petrinet.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import net.odyne.petrinet.entities.Customer;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<Customer, Long> {

	public Customer findByUsername(@Param("username") String username);
	
}
