package ru.itsjava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.Pet;
import ru.itsjava.repository.PetRepository;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService{
    private final PetRepository petRepository;

    @Transactional
    @Override
    public void changePet(String oldSpecies, String newSpecies) {
        Pet pet = petRepository.getBySpecies(oldSpecies).get();
        pet.setSpecies(newSpecies);
        petRepository.save(pet);
        System.out.println("Successfully saved!");
    }

    @Transactional(readOnly = true)
    @Override
    public void printPet(String species) {
        Pet pet = petRepository.getBySpecies(species).get();
        System.out.println((pet));
    }
}
