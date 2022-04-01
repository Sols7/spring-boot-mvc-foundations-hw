package ru.itsjava.service;

public interface PetService {
    void changePet (String oldSpecies, String newSpecies);
    void printPet (String species);
}
