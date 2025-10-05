package dk.via.server.persistence.animal;

import dk.via.domain.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAOImpl implements AnimalDAO {

    private static AnimalDAOImpl instance;

    private AnimalDAOImpl() {
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

    public static synchronized AnimalDAOImpl getInstance() {
        if (instance == null) {
            instance = new AnimalDAOImpl();
        }
        return instance;
    }

    @Override
    public void create(Animal animal) {
        System.out.println(animal.toString());

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO pro3.animal (type, reg_num, weight) VALUES (?, ?, ?);");
            statement.setString(1, animal.getType());
            statement.setInt(2, animal.getRegNumber());
            statement.setDouble(3, animal.getWeight());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Animal getAnimalById(int id) {
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM pro3.animal WHERE reg_num = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String type = resultSet.getString("type");
                double weight = resultSet.getDouble("weight");
                return new Animal(type, id, weight);
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
                    "DELETE FROM pro3.animal WHERE reg_num = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Animal update(Animal animal) {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE pro3.animal SET type = ?, weight = ? WHERE reg_num = ?"
            );
            statement.setString(1, animal.getType());
            statement.setDouble(2, animal.getWeight());
            statement.setInt(3, animal.getRegNumber());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return animal;
    }

    @Override
    public List<Animal> getAll() {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM pro3.animal"
            );
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Animal> animals = new ArrayList<>();
            while(resultSet.next()) {
                int regNum = resultSet.getInt("reg_num");
                String type = resultSet.getString("type");
                double weight = resultSet.getDouble("weight");
                Animal animal = new Animal(type, regNum, weight);
                animals.add(animal);
            }
            return animals;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return List.of();
    }
}
