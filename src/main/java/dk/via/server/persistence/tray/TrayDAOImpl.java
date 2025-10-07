package dk.via.server.persistence.tray;

import dk.via.domain.Tray;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrayDAOImpl implements TrayDAO {

    private static TrayDAOImpl instance;

    private TrayDAOImpl() {
        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static TrayDAOImpl getInstance() {
        // double-checked locking
        if(instance == null) {
            synchronized (TrayDAOImpl.class)
            {
                if (instance == null) {
                    instance = new TrayDAOImpl();
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
    public void create(Tray tray) {
        System.out.println(tray.toString());
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO pro3.tray (tray_num, part_type, capacity, current_weight) VALUES (?, ?, ?, ?);");
            statement.setInt(1, tray.getTrayNumber());
            statement.setString(2, tray.getPartType());
            statement.setDouble(3, tray.getMaxCapacity());
            statement.setDouble(4, tray.getCurrentWeight());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Error creating tray");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedNumber = generatedKeys.getInt(1);
                    tray.setTrayNumber(generatedNumber);
                    System.out.println("Created tray with number: " + generatedNumber);
                } else {
                    throw new SQLException("Error creating tray");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Tray getTrayById(int id) {
        try (Connection connection = getConnection())
        {
            Tray tray;
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM pro3.tray WHERE tray_num = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                tray = new Tray(
                        resultSet.getInt("tray_num"),
                        resultSet.getString("part_type"),
                        resultSet.getDouble("capacity"),
                        resultSet.getDouble("current_weight")
                );
                return tray;
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
                    "DELETE FROM pro3.tray WHERE tray_num = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Tray update(Tray tray) {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE pro3.tray SET part_type = ?, capacity = ?, current_weight = ? WHERE tray_num = ?"
            );
            statement.setString(1, tray.getPartType());
            statement.setDouble(2, tray.getMaxCapacity());
            statement.setDouble(3, tray.getCurrentWeight());
            statement.setInt(4, tray.getTrayNumber());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return tray;
    }

    @Override
    public List<Tray> getAll() {
        List<Tray> trays = new ArrayList<>();
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM pro3.tray");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Tray tray = new Tray(
                        resultSet.getInt("tray_num"),
                        resultSet.getString("part_type"),
                        resultSet.getDouble("capacity"),
                        resultSet.getDouble("current_weight")
                );
                trays.add(tray);
            }
            return trays;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return List.of();
    }
}
