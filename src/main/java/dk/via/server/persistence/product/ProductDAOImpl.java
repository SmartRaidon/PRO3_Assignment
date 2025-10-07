package dk.via.server.persistence.product;

import dk.via.domain.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private static ProductDAOImpl instance;

    private ProductDAOImpl() {
        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static ProductDAOImpl getInstance() {
        // double-checked locking
        if(instance == null) {
            synchronized (ProductDAOImpl.class)
            {
                if (instance == null) {
                    instance = new ProductDAOImpl();
                }
            }
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres?currentSchema=pro3",
                "postgres", "GroupFive"
        );
    }

    @Override
    public void create(Product product) {
        System.out.println(product.toString());
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO pro3.product (product_num, type) VALUES (?, ?);");
            statement.setInt(1, product.getProductNumber());
            statement.setString(2, product.getType());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Error creating product");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedNumber = generatedKeys.getInt(1);
                    product.setProductNumber(generatedNumber);
                    System.out.println("Created product with number: " + generatedNumber);
                } else {
                    throw new SQLException("Error creating product");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProductById(int id) {
        try (Connection connection = getConnection())
        {
            Product product;
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM pro3.product WHERE product_num = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                    product = new Product(
                            resultSet.getInt("product_num"),
                            resultSet.getString("type")
                );
                return product;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM pro3.product WHERE product_num = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product update(Product product) {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE pro3.product SET type = ? WHERE product_num = ?"
            );
            statement.setString(1, product.getType());
            statement.setInt(2, product.getProductNumber());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM pro3.product");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("product_num"),
                        resultSet.getString("type")
                );
                products.add(product);
            }
            return products;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return List.of();
    }
}
