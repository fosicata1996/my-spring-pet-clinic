package guru.springframework.myspringpetclinic.repositories;

import guru.springframework.myspringpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
