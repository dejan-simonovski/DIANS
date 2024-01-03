package mk.com.kinmkd.kinmkd.service;

import mk.com.kinmkd.kinmkd.model.User;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserService {
    User register(String email, String password, String repeatPassword);
    User login(String email, String password);
}
