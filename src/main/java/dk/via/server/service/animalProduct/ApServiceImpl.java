package dk.via.server.service.animalProduct;

import dk.via.server.persistence.animalProduct.AnimalProductDAO;

import java.util.List;

public class ApServiceImpl implements ApService {

    private final AnimalProductDAO dao;

    public ApServiceImpl(AnimalProductDAO animalProductDAO) {
        this.dao = animalProductDAO;
    }

    @Override
    public List<Integer> getAnimalsByProduct(int productId) {
        return dao.getAnimalsByProduct(productId);
    }

    @Override
    public List<Integer> getProductsByAnimal(int animalId) {
        return dao.getProductsByAnimal(animalId);
    }
}
