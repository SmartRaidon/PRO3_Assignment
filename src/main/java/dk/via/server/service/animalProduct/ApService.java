package dk.via.server.service.animalProduct;

import java.util.List;

public interface ApService {
    List<Integer> getAnimalsByProduct(int productId);
    List<Integer> getProductsByAnimal(int animalId);
}
