package ua.vedroid.dao3.security;

import ua.vedroid.dao3.exceptions.AuthenticationException;
import ua.vedroid.dao3.lib.Inject;
import ua.vedroid.dao3.lib.Service;
import ua.vedroid.dao3.model.Driver;
import ua.vedroid.dao3.service.DriverService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverService service;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Driver driverFromDB = service.findByLogin(login)
                .orElseThrow(() -> new AuthenticationException("Incorrect login or password"));
        if (driverFromDB.getPassword().equals(password)) {
            return driverFromDB;
        }
        throw new AuthenticationException("Incorrect login or password");
    }
}
