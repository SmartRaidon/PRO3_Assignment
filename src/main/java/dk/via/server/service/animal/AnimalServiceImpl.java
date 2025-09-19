package dk.via.server.service.animal;

import dk.via.domain.Animal;
import dk.via.server.persistence.animal.AnimalDAO;

import java.util.List;

public class AnimalServiceImpl implements AnimalService {


    private final AnimalDAO animalDAO;

    public AnimalServiceImpl(AnimalDAO animalDao) {
        animalDAO = animalDao;
    }
    @Override
    public Animal registerAnimal(String regNumber, double weight) {
        Animal animal = new Animal();
        return animalDAO.create(animal);
    }

    @Override
    public Animal getAnimal(int id) {
        return animalDAO.findById(id);

    }

    @Override
    public List<Animal> getAllAnimals() {
        return animalDAO.findAll();
    }
}
