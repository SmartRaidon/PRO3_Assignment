package dk.via.server.service.product;

import dk.via.domain.Product;
import dk.via.server.persistence.product.ProductDAO;
import dk.via.server.persistence.product.ProductDAOImpl;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Product createProduct(String type) {
        Product product = new Product();
        product.setType(type);
        return productDAO.create(product);
    }

    @Override
    public Product getProduct(int id) {
        return productDAO.getProductById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.getAll();
    }

    @Override
    public void delete(int id) {
        productDAO.delete(id);
    }

    @Override
    public Product update(Product product) {
        return productDAO.update(product);
    }
}
