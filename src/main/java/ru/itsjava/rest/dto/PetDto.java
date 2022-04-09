package ru.itsjava.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itsjava.domain.Pet;

@Data
@AllArgsConstructor
public class PetDto {
    private String id;
    private String species;

    public static Pet fromDto(PetDto petDto) {
        long id = Long.parseLong(petDto.id);

        return new Pet(id, petDto.species);
    }

    public static PetDto toDto(Pet pet) {
        String id = String.valueOf(pet.getId());
        String species = pet.getSpecies();

        return new PetDto(id, species);
    }
}
