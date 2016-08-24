package net.odyne.petrinet.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import net.odyne.petrinet.entities.Petrinet;

@RepositoryRestResource(collectionResourceRel = "petrinets", path = "petrinets")
public interface PetrinetRepository extends PagingAndSortingRepository<Petrinet, Long> {

}
