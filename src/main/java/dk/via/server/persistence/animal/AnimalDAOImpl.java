package dk.via.server.persistence.animal;

import dk.via.domain.Animal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AnimalDAOImpl implements AnimalDAO {

    private final Connection connection;

    public AnimalDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Animal create(Animal animal) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO animals (reg_number, weight) VALUES (?, ?)"
            );
            stmt.setString(1, " data");
           // stmt.setDouble(2, "data");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("again data");
            }
            return animal;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating animal", e);
        }
    }

    @Override
    public Animal findById(int id) {
        return null;
    }

    @Override
    public List<Animal> findAll() {
        return null;
       // return List<Animal>;
    }
}
