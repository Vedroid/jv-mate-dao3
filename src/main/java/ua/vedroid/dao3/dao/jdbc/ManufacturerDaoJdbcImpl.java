package ua.vedroid.dao3.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ua.vedroid.dao3.dao.ManufacturerDao;
import ua.vedroid.dao3.exceptions.DataProcessingException;
import ua.vedroid.dao3.lib.Dao;
import ua.vedroid.dao3.model.Manufacturer;
import ua.vedroid.dao3.util.ConnectionUtil;

@Dao
public class ManufacturerDaoJdbcImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        String query = "INSERT INTO manufacturers"
                + "(manufacturer_name, manufacturer_country) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getCountry());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                manufacturer.setId(resultSet.getObject("manufacturer_id", Long.class));
            }
            resultSet.close();
            return manufacturer;
        } catch (SQLException ex) {
            throw new DataProcessingException("Insert " + manufacturer
                    + " to database is failed", ex);
        }
    }

    @Override
    public Optional<Manufacturer> getById(Long manufacturerId) {
        String query = "SELECT * FROM manufacturers "
                + "WHERE manufacturer_id = ? AND is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, manufacturerId);
            ResultSet resultSet = statement.executeQuery();
            Optional<Manufacturer> manufacturerFromResultSet = Optional.empty();
            if (resultSet.next()) {
                manufacturerFromResultSet = Optional.of(getManufacturerFromResultSet(resultSet));
            }
            resultSet.close();
            return manufacturerFromResultSet;
        } catch (SQLException ex) {
            throw new DataProcessingException("Get manufacturer with id="
                    + manufacturerId + " is failed", ex);

        }
    }

    @Override
    public List<Manufacturer> getAll() {
        String query = "SELECT * FROM manufacturers WHERE is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<Manufacturer> manufacturers = new ArrayList<>();
            while (resultSet.next()) {
                manufacturers.add(getManufacturerFromResultSet(resultSet));
            }
            resultSet.close();
            return manufacturers;
        } catch (SQLException ex) {
            throw new DataProcessingException("Get all manufacturers is failed", ex);
        }
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        String query = "UPDATE manufacturers "
                + "SET manufacturer_name = ?, manufacturer_country = ? "
                + "WHERE is_deleted = false AND manufacturer_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getCountry());
            statement.setLong(3, manufacturer.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataProcessingException("Update " + manufacturer + " is failed", ex);
        }
        return manufacturer;
    }

    @Override
    public boolean deleteById(Long manufacturerId) {
        String query = "UPDATE manufacturers SET is_deleted = true "
                + "WHERE manufacturer_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, manufacturerId);
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DataProcessingException("Delete of manufacturer with id="
                    + manufacturerId + " is failed", ex);
        }
    }

    private Manufacturer getManufacturerFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("manufacturer_id");
        String name = resultSet.getString("manufacturer_name");
        String country = resultSet.getString("manufacturer_country");
        Manufacturer manufacturer = new Manufacturer(name, country);
        manufacturer.setId(id);
        return manufacturer;
    }
}
