package dk.via.server.persistence.animalProduct;

import dk.via.domain.Animal;
import dk.via.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApDAOImpl implements AnimalProductDAO {

    private static ApDAOImpl instance;

    private ApDAOImpl() {
        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres?currentSchema=pro3",
                "postgres", "GroupFive"
        );
    }

    public static ApDAOImpl getInstance() {
        if (instance == null) {
            synchronized (ApDAOImpl.class) {
                if (instance == null) {
                    instance = new ApDAOImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Integer> getAnimalsByProduct(int productId) {
        List<Integer> result = new ArrayList<>();
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("""
                SELECT DISTINCT a.reg_num
                FROM Animal a
                JOIN Part p ON a.reg_num = p.origin_num
                JOIN Tray t ON p.tray_num = t.tray_num
                JOIN trayproduct tpr ON t.tray_num = tpr.tray_num
                JOIN Product pr ON tpr.product_num = pr.product_num
                WHERE pr.product_num = ?
            """);

            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                result.add(resultSet.getInt("reg_num"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Integer> getProductsByAnimal(int animalId) {
        List<Integer> result = new ArrayList<>();
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("""
                SELECT DISTINCT pr.product_num
                FROM Product pr
                JOIN trayproduct tpr ON pr.product_num = tpr.product_num
                JOIN Tray t ON tpr.tray_num = t.tray_num
                JOIN Part p ON t.tray_num = p.tray_num
                JOIN Animal a ON p.origin_num = a.reg_num
                WHERE a.reg_num = ?
            """);

            statement.setInt(1, animalId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                result.add(resultSet.getInt("product_num"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
