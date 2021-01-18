package ua.vedroid.dao3.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ua.vedroid.dao3.dao.DriverDao;
import ua.vedroid.dao3.exceptions.DataProcessingException;
import ua.vedroid.dao3.lib.Dao;
import ua.vedroid.dao3.model.Driver;
import ua.vedroid.dao3.util.ConnectionUtil;

@Dao
public class DriverDaoJdbcImpl implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        String query = "INSERT INTO drivers(name, licence_number) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getLicenceNumber());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                driver.setId(resultSet.getLong("id"));
            }
            return driver;
        } catch (SQLException ex) {
            throw new DataProcessingException("Insert " + driver + " to database is failed", ex);
        }
    }

    @Override
    public Optional<Driver> getById(Long id) {
        String query = "SELECT * FROM drivers WHERE id = ? AND is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Driver> driverFromResultSet = Optional.empty();
            if (resultSet.next()) {
                driverFromResultSet = Optional.of(getDriverFromResultSet(resultSet));
            }
            return driverFromResultSet;
        } catch (SQLException ex) {
            throw new DataProcessingException("Get driver with id="
                    + id + " is failed", ex);
        }
    }

    @Override
    public List<Driver> getAll() {
        String query = "SELECT * FROM drivers WHERE is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<Driver> drivers = new ArrayList<>();
            while (resultSet.next()) {
                drivers.add(getDriverFromResultSet(resultSet));
            }
            return drivers;
        } catch (SQLException ex) {
            throw new DataProcessingException("Get all drivers is failed", ex);
        }
    }

    @Override
    public Driver update(Driver driver) {
        String query = "UPDATE drivers SET name = ?, licence_number = ? "
                + "WHERE id = ? AND is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getLicenceNumber());
            statement.setLong(3, driver.getId());
            statement.executeUpdate();
            return driver;
        } catch (SQLException ex) {
            throw new DataProcessingException("Update " + driver + " is failed", ex);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String query = "UPDATE drivers SET is_deleted = true WHERE id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DataProcessingException("Delete of driver with id="
                    + id + " is failed", ex);
        }
    }

    private Driver getDriverFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getObject("id", Long.class);
        String name = resultSet.getString("name");
        String licenceNumber = resultSet.getString("licence_number");
        Driver driver = new Driver(name, licenceNumber);
        driver.setId(id);
        return driver;
    }
}
