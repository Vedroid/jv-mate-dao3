package ua.vedroid.dao3.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ua.vedroid.dao3.dao.CarDao;
import ua.vedroid.dao3.exceptions.DataProcessingException;
import ua.vedroid.dao3.lib.Dao;
import ua.vedroid.dao3.model.Car;
import ua.vedroid.dao3.model.Driver;
import ua.vedroid.dao3.model.Manufacturer;
import ua.vedroid.dao3.util.ConnectionUtil;

@Dao
public class CarDaoJdbcImpl implements CarDao {
    @Override
    public Car create(Car car) {
        String query = "INSERT INTO cars(m_id, model) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, car.getManufacturer().getId());
            statement.setString(2, car.getModel());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                car.setId(resultSet.getLong("id"));
            }
            if (!car.getDrivers().isEmpty()) {
                addDrivers(connection, car);
            }
            return car;
        } catch (SQLException ex) {
            throw new DataProcessingException("Insert " + car + " to database is failed", ex);
        }
    }

    @Override
    public Optional<Car> getById(Long id) {
        String query = "SELECT * FROM cars "
                + "JOIN manufacturers m on m.id = cars.m_id "
                + "LEFT JOIN cars_drivers cd on cars.id = cd.car_id "
                + "LEFT JOIN drivers d on d.id = cd.driver_id "
                + "WHERE cars.id = ?"
                + " AND cars.is_deleted = FALSE;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getCarFromResultSet(connection, resultSet));
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new DataProcessingException("Get car with id="
                    + id + " is failed", ex);
        }
    }

    @Override
    public List<Car> getAll() {
        String query = "SELECT * FROM cars JOIN manufacturers m on m.id = cars.m_id "
                + "WHERE cars.is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                cars.add(getCarFromResultSet(connection, resultSet));
            }
            return cars;
        } catch (SQLException ex) {
            throw new DataProcessingException("Get all cars is failed", ex);
        }
    }

    @Override
    public Car update(Car car) {
        String query = "UPDATE cars SET m_id = ?, model = ?"
                + " WHERE id = ? AND is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            removeDrivers(connection, car.getId());
            statement.setLong(1, car.getManufacturer().getId());
            statement.setString(2, car.getModel());
            statement.setLong(3, car.getId());
            statement.executeUpdate();
            if (!car.getDrivers().isEmpty()) {
                addDrivers(connection, car);
            }
            return car;
        } catch (SQLException ex) {
            throw new DataProcessingException("Update " + car + " is failed", ex);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String query = "UPDATE cars SET is_deleted = true WHERE id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DataProcessingException("Delete of car with id="
                    + id + " is failed", ex);
        }
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        String query = "SELECT * FROM cars c "
                + "LEFT JOIN cars_drivers cd on c.id = cd.car_id "
                + "LEFT JOIN drivers d on cd.driver_id = d.id "
                + " LEFT JOIN manufacturers m on m.id = c.m_id "
                + "WHERE d.id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, driverId);
            ResultSet resultSet = statement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                cars.add(getCarFromResultSet(connection, resultSet));
            }
            return cars;
        } catch (SQLException ex) {
            throw new DataProcessingException("", ex);
        }
    }

    private void removeDrivers(Connection connection, Long carId) {
        String query = "DELETE FROM cars_drivers WHERE car_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, carId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataProcessingException("Remove drivers with car_id=" + carId
                    + " is failed", ex);
        }
    }

    private void addDrivers(Connection connection, Car car) {
        String query = "INSERT INTO cars_drivers(car_id, driver_id) VALUES (?, ?)";
        for (Driver driver : car.getDrivers()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, car.getId());
                statement.setLong(2, driver.getId());
                statement.executeUpdate();
            } catch (SQLException ex) {
                throw new DataProcessingException("Add driver=" + driver + " to car=" + car
                        + " is failed", ex);
            }
        }
    }

    private Car getCarFromResultSet(Connection connection, ResultSet resultSet)
            throws SQLException {
        Long id = resultSet.getObject("id", Long.class);
        Manufacturer manufacturer = getManufacturerFromResultSet(resultSet);
        String model = resultSet.getString("model");
        Car car = new Car(manufacturer, model);
        car.setId(id);
        car.setDrivers(getDriversByCar(connection, car.getId()));
        return car;
    }

    private List<Driver> getDriversByCar(Connection connection, Long carId) {
        String query = "SELECT * FROM cars_drivers cd JOIN drivers d on d.id = cd.driver_id "
                + "WHERE car_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, carId);
            ResultSet resultSet = statement.executeQuery();
            List<Driver> drivers = new ArrayList<>();
            while (resultSet.next()) {
                drivers.add(getDriverFromResultSet(resultSet));
            }
            return drivers;
        } catch (SQLException ex) {
            throw new DataProcessingException("Get drivers by carID=" + carId
                    + " is failed", ex);
        }
    }

    private Driver getDriverFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String licenceNumber = resultSet.getString("licence_number");
        Driver driver = new Driver(name, licenceNumber);
        driver.setId(id);
        return driver;
    }

    private Manufacturer getManufacturerFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getObject("m_id", Long.class);
        String name = resultSet.getString("name");
        String country = resultSet.getString("country");
        Manufacturer manufacturer = new Manufacturer(name, country);
        manufacturer.setId(id);
        return manufacturer;
    }
}
