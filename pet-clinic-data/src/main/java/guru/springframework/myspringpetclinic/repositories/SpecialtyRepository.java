package guru.springframework.myspringpetclinic.repositories;

import guru.springframework.myspringpetclinic.model.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
