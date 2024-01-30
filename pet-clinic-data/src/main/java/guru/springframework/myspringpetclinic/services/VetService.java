package guru.springframework.myspringpetclinic.services;

import guru.springframework.myspringpetclinic.model.Vet;

import java.util.Set;

public interface VetService {


    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
