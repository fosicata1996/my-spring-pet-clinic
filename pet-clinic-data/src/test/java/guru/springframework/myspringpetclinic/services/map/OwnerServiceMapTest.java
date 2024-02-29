package guru.springframework.myspringpetclinic.services.map;

import guru.springframework.myspringpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    private static final Long OWNER_ID = 1L;
    private static final String LAST_NAME = "Smith";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());

        ownerServiceMap.save(Owner.builder().id(OWNER_ID).lastName(LAST_NAME).build());
    }

    @Test
    void findAll() {
        assertEquals(1, ownerServiceMap.findAll().size());
    }

    @Test
    void findById() {
        assertEquals(OWNER_ID, ownerServiceMap.findById(OWNER_ID).getId());
    }

    @Test
    void saveExistingId() {
        long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        Owner savedOwner = ownerServiceMap.save(owner2);

        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(OWNER_ID));

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(OWNER_ID);

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner smith = ownerServiceMap.findByLastName(LAST_NAME);

        assertNotNull(smith);
        assertEquals(OWNER_ID, smith.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner smith = ownerServiceMap.findByLastName("foo");

        assertNull(smith);
    }
}