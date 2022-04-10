package ru.itsjava.service;

import ru.itsjava.domain.Pet;

public interface PetService {
    void changePet (String oldSpecies, String newSpecies);
    void printPet (String species);
    void createPet(Pet pet);
    Pet getPetById(long id);
    void deletePetById(long id);
}
