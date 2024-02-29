package guru.springframework.myspringpetclinic.services.spring_data_jpa;

import guru.springframework.myspringpetclinic.model.Owner;
import guru.springframework.myspringpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerServiceJpaTest {

    public static final String SMITH = "Smith";
    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerServiceJpa ownerService;

    private Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(1L).lastName(SMITH).build();
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnersSet = new HashSet<>();
        returnOwnersSet.add(Owner.builder().id(1L).build());
        returnOwnersSet.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(returnOwnersSet);

        Set<Owner> owners = ownerService.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(any())).thenReturn(Optional.of(returnOwner));

        Owner owner = ownerService.findById(1L);

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(any())).thenReturn(Optional.empty());

        Owner owner = ownerService.findById(1L);

        assertNull(owner);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(returnOwner);

        assertNotNull(ownerService.save(ownerToSave));
        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        ownerService.delete(returnOwner);

        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        ownerService.deleteById(1L);

        verify(ownerRepository).deleteById(any());
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        assertEquals(SMITH, ownerService.findByLastName(SMITH).getLastName());
        verify(ownerRepository).findByLastName(any());
    }
}