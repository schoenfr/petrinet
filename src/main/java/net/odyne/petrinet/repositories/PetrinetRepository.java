package net.odyne.petrinet.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeLinkDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeLinkSave;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import net.odyne.petrinet.entities.Petrinet;

@RepositoryRestResource(collectionResourceRel = "petrinets", path = "petrinets")
public interface PetrinetRepository extends Repository<Petrinet, Long> {
	
	@PreAuthorize("principal.username == 'timo'")
	public Iterable<Petrinet> findAll();

	@PreAuthorize("principal.username == 'timo'")
	public Iterable<Petrinet> findAll(Sort sort);

	@PreAuthorize("principal.username == 'timo'")
	public Page<Petrinet> findAll(Pageable pageable);

	@PostAuthorize("returnObject.owner == principal.customer")
//	@PostFilter("hasPermission(filterObject, 'read') or hasPermission(filterObject, 'admin')")
	public Petrinet findOne(Long id);

	
	@Service
	@RepositoryEventHandler(Petrinet.class)
//	@Secured("ROLE_ADMIN")
	public static class PetrinetEventHandler {
		
//		  @PreAuthorize("principal.username == petrinet.username")
		  @HandleBeforeSave
		  @HandleBeforeCreate
		  @HandleBeforeLinkSave
		  @HandleBeforeDelete
		  @HandleBeforeLinkDelete
		  public void handle(Petrinet petrinet) {
			  System.out.println("handling " + petrinet);
			  throw new AccessDeniedException("NO! :(");
		  }
		
	}
	
}
