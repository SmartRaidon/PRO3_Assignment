package dk.via.server.service.product;

import dk.via.domain.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(String type);
    Product getProduct(int id);
    List<Product> getAllProducts();
    void delete(int id);
    Product update (Product product);
}
