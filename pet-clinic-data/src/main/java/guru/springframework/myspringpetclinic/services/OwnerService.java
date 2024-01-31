package guru.springframework.myspringpetclinic.services;

import guru.springframework.myspringpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
