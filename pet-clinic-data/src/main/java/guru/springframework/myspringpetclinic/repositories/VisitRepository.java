package guru.springframework.myspringpetclinic.repositories;

import guru.springframework.myspringpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
