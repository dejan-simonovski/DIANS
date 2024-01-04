package mk.com.kinmkd.kinmkd.service;

import mk.com.kinmkd.kinmkd.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserService extends UserDetailsService {
    User register(String email, String password, String repeatPassword);
    User findByEmail(String email);
}
