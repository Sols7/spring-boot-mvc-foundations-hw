package ru.itsjava.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Pet;
import ru.itsjava.repository.PetRepository;
import ru.itsjava.service.PetService;
import ru.itsjava.service.PetServiceImpl;

import java.util.NoSuchElementException;


@DataJpaTest
@Import(PetServiceImpl.class)
public class PetServiceImplTest {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetService petService;

    @Test
    public void shouldHaveCorrectCreatePet() {
        var createPet = new Pet(4L, "createPet");
        petService.createPet(createPet);
        var actualPet = petService.getPetById(4L);

        Assertions.assertEquals(createPet, actualPet);
    }

    @Test
    public void shouldHaveCorrectGetPetById() {
        var expectedPet = petRepository.findById(1L).get();
        var actualPet = petService.getPetById(1L);

        Assertions.assertEquals(expectedPet, actualPet);
    }

    @Test
    public void shouldHaveCorrectDeletePet() {
        NoSuchElementException elementException = Assertions.assertThrows(NoSuchElementException.class, () -> {
            petService.deletePet(petService.getPetById(3L));
            var deletedPet = petService.getPetById(3L);

            Assertions.assertNull(deletedPet);
        });

        Assertions.assertEquals("No value present", elementException.getMessage());
    }

    @Test
    public void shouldHaveCorrectUpdatePet() {
        var updatePet = petService.getPetById(1L);
        updatePet.setSpecies("updatePet");
        petService.updatePet(updatePet);
        var actualPet = petService.getPetById(1L);

        Assertions.assertEquals("updatePet", actualPet.getSpecies());
    }

    @Test
    public void shouldHaveCorrectGetAllPets() {
        var expectedPets = petRepository.findAll();
        var actualPets = petService.getAllPets();

        System.out.println("actualPets = " + actualPets);
        Assertions.assertEquals(expectedPets, actualPets);
    }
}
