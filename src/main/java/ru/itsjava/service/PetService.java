package ru.itsjava.service;

import ru.itsjava.domain.Pet;

import java.util.List;

public interface PetService {
    void createPet(Pet pet);

    Pet getPetById(long id);

    void deletePet(Pet pet);

    List<Pet> getAllPets();

    void updatePet(Pet pet);
}
