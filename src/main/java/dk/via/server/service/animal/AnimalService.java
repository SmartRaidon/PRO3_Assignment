package dk.via.server.service.animal;

import dk.via.domain.Animal;

import java.util.List;

public interface AnimalService {

    Animal registerAnimal(String regNumber, double weight);
    Animal getAnimal(int id);
    List<Animal> getAllAnimals();
}
