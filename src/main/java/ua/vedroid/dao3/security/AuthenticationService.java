package ua.vedroid.dao3.security;

import ua.vedroid.dao3.exceptions.AuthenticationException;
import ua.vedroid.dao3.model.Driver;

public interface AuthenticationService {
    Driver login(String login, String password) throws AuthenticationException;
}
