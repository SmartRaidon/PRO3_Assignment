package dk.via.server.persistence.animalProduct;

import java.util.List;

public interface AnimalProductDAO {
    List<Integer> getAnimalsByProduct(int productId);
    List<Integer> getProductsByAnimal(int animalId);
}
