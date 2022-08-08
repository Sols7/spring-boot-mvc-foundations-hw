package ru.itsjava.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Pet;
import ru.itsjava.repository.PetRepository;
import ru.itsjava.repository.UserRepository;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@Import(PetServiceImpl.class)
public class PetServiceImplTest {

    @Configuration
    static class MyConfiguration {
        Pet pet1 = new Pet(1, "testPet1");
        Pet pet2 = new Pet(2, "testPet2");
        List<Pet> pets = List.of(pet1, pet2);

        @Bean
        public PetRepository petRepositoryMock() {
            PetRepository mockPetRepository = Mockito.mock(PetRepository.class);
            when(mockPetRepository.findAll()).thenReturn(pets);
            return mockPetRepository;
        }

        @Bean
        public UserRepository userRepositoryMock() {
            return Mockito.mock(UserRepository.class);
        }

        @Bean
        public PetService petService() {
            return new PetServiceImpl(petRepositoryMock(), userRepositoryMock());
        }
    }

    @Autowired
    private PetService petService;

    @MockBean
    PetRepository petRepositoryMock;

    @Test
    public void getAllPetsTest() {
        Pet pet1 = new Pet(1, "testPet1");
        Pet pet2 = new Pet(2, "testPet2");
        List<Pet> pets = List.of(pet1, pet2);

        Mockito.when(petRepositoryMock.findAll()).thenReturn(pets);
        var actualPets = petService.getAllPets();

        Assertions.assertEquals(pets, actualPets);
    }
}
