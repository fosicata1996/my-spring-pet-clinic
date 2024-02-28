package guru.springframework.myspringpetclinic.repositories;

import guru.springframework.myspringpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
