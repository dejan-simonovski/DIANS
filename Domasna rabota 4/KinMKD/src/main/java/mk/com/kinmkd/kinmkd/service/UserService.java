package mk.com.kinmkd.kinmkd.service;

import mk.com.kinmkd.kinmkd.model.User;

public interface UserService {
    User register(String email, String password, String repeatPassword);
    User login(String email, String password);
}
