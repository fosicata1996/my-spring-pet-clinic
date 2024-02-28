package guru.springframework.myspringpetclinic.services.spring_data_jpa;

import guru.springframework.myspringpetclinic.model.Pet;
import guru.springframework.myspringpetclinic.repositories.PetRepository;
import guru.springframework.myspringpetclinic.services.PetService;

import java.util.HashSet;
import java.util.Set;

public class PetServiceJpa implements PetService {
    private final PetRepository petRepository;

    public PetServiceJpa(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long aLong) {
        return petRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petRepository.deleteById(aLong);
    }
}
