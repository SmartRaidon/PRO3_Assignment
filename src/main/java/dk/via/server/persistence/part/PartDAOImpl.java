package dk.via.server.persistence.part;

import dk.via.domain.Part;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class PartDAOImpl implements PartDAO {

    private static PartDAOImpl instance;

    private PartDAOImpl() {
    try{
        DriverManager.registerDriver(new org.postgresql.Driver());
    }
    catch(Exception e) {
        e.printStackTrace();
    }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres?currentSchema=pro3",
                "postgres", "GroupFive"
        );
    }

    public static PartDAOImpl getInstance() {
        if (instance == null) {
            instance = new PartDAOImpl();
        }
        return instance;
    }

    @Override
    public void create(Part part) {
        System.out.println(part.toString());

        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO pro3.part (reg_num, tray_num, origin_num, product_num, type, weight)" +
                            "VALUES (?, ?, ?, ?, ?, ?);");
                    statement.setInt(1, part.getRegNumber());
                    statement.setInt(2, part.getTrayNumber());
                    statement.setInt(3, part.getOriginNumber());
                    statement.setInt(4, part.getProductNumber());
                    statement.setString(5, part.getType());
                    statement.setDouble(6, part.getWeight());
                    statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Part getPartById(int id) {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM pro3.part WHERE reg_num = ?"
            );
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int trayNumber = resultSet.getInt("tray_num");
                int originNumber = resultSet.getInt("origin_num");
                int productNumber = resultSet.getInt("product_num");
                String type = resultSet.getString("type");
                double weight = resultSet.getDouble("weight");

                return new Part(id, trayNumber, originNumber, productNumber, type, weight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
    try(Connection connection = getConnection()) {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM pro3.part WHERE reg_num = ?");
        statement.setInt(1, id);
        statement.executeUpdate();
    }
    catch (SQLException e) {
        e.printStackTrace();
    }
    }

    @Override
    public Part update(Part part) {
       try(Connection connection = getConnection()) {
           PreparedStatement statement = connection.prepareStatement(
                   "UPDATE pro3.part SET tray_num = ?, origin_num = ?, product_num = ?, type = ?, weight = ? WHERE reg_num = ?");
           statement.setInt(1, part.getTrayNumber());
           statement.setInt(2, part.getOriginNumber());
           statement.setInt(3, part.getProductNumber());
           statement.setString(4, part.getType());
           statement.setDouble(5, part.getWeight());
           statement.executeUpdate();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return null;
    }

    @Override
    public List<Part> getAllParts() {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM pro3.part"
            );
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Part> parts = new ArrayList<>();
            while(resultSet.next()) {
                int regNum = resultSet.getInt("reg_num");
                int trayNum = resultSet.getInt("tray_num");
                int originNum = resultSet.getInt("origin_num");
                int productNum = resultSet.getInt("product_num");
                String type = resultSet.getString("type");
                double weight = resultSet.getDouble("weight");
                Part part = new Part(regNum, trayNum, originNum, productNum, type, weight);
                parts.add(part);
            }
            return parts;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
