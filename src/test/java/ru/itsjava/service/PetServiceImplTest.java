package ru.itsjava.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Pet;
import ru.itsjava.repository.PetRepository;

import java.util.ArrayList;
import java.util.Arrays;

@DataJpaTest
@Import(PetServiceImpl.class)
public class PetServiceImplTest {

    @Autowired
    private PetService petService;

    @MockBean
    PetRepository petRepositoryMock;

    @Test
    public void getAllUsersTest() {
        Pet pet1 = new Pet(1, "testPet1");
        Pet pet2 = new Pet(2, "testPet2");
        ArrayList<Pet> pets = new ArrayList<>(Arrays.asList(pet1, pet2));

        Mockito.when(petRepositoryMock.findAll()).thenReturn(pets);
        var actualPets = petService.getAllPets();

        Assertions.assertEquals(pets, actualPets);
    }
}
