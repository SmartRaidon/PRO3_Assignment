package dk.via.server.persistence.animal;

import dk.via.domain.Animal;

import java.util.List;

public interface AnimalDAO {

    Animal create(Animal animal);
    Animal findById(int id);
    List<Animal> findAll();
}
