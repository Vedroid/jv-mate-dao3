package ua.vedroid.dao3.model;

import java.util.Objects;

public class Driver {
    private Long id;
    private String login;
    private String password;
    private String name;
    private String licenceNumber;

    public Driver(String login, String password, String name, String licenceNumber) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.licenceNumber = licenceNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    @Override
    public String toString() {
        return "Driver{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", licenceNumber='" + licenceNumber + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Driver driver = (Driver) o;
        return Objects.equals(id, driver.id)
                && Objects.equals(name, driver.name)
                && Objects.equals(licenceNumber, driver.licenceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, licenceNumber);
    }
}
