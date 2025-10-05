package dk.via.server.persistence.animal;

import dk.via.domain.Animal;

import java.util.List;

public interface AnimalDAO {

    void create(Animal animal);
    Animal getAnimalById(int id);
    void delete(int id);
    Animal update (Animal animal);
    List<Animal> getAll();
}
