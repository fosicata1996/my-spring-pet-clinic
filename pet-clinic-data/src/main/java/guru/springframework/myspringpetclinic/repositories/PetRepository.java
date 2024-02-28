package guru.springframework.myspringpetclinic.repositories;

import guru.springframework.myspringpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
