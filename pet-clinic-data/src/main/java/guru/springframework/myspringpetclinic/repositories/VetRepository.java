package guru.springframework.myspringpetclinic.repositories;

import guru.springframework.myspringpetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
