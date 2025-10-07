package dk.via.server.persistence.product;
import dk.via.domain.Product;

import java.util.List;

public interface ProductDAO {
    void create(Product product);
    Product getProductById(int id);
    void delete(int id);
    Product update (Product product);
    List<Product> getAll();
}
