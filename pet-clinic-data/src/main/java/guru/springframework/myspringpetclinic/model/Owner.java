package guru.springframework.myspringpetclinic.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person {

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    @Builder
    public Owner(Long id, String firstName, String lastName, String address, String city, String telephone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        if (pets != null) {
            this.pets = pets;
        }
    }

    public void add(Pet pet) {
        this.pets.add(pet);
        pet.setOwner(this);
    }

    public Pet getPet(String name, boolean ignoreNew) {
        return pets.stream()
                .filter(pet -> !ignoreNew || !pet.isNew())
                .filter(pet -> pet.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public Pet getPet(String name) {
        return getPet(name, false);
    }
}
