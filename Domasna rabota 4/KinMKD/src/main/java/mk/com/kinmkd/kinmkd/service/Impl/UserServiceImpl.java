package mk.com.kinmkd.kinmkd.service.Impl;

import lombok.AllArgsConstructor;
import mk.com.kinmkd.kinmkd.model.User;
import mk.com.kinmkd.kinmkd.model.exception.*;
import mk.com.kinmkd.kinmkd.repository.UserRepository;
import mk.com.kinmkd.kinmkd.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public User register(String email, String password, String repeatPassword) {
        if (!password.equals(repeatPassword)) {
            throw new PasswordsNotMatchingException();
        }
        if(!(password.length() >= 6 && password.matches(".*[0-9]+.*") && password.matches(".*[a-zA-Z]+.*")))
            throw new PasswordWeakException();
        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailTakenException(email);
        }
        User user = new User(email, password);
        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {
        Optional<User> container = userRepository.findByEmail(email);
        if (container.isEmpty()) {
            throw new EmailNotExistingException(email);
        }
        User user = container.get();
        if (!user.verifyPassword(password)) {
            throw new IncorrectPasswordException();
        }
        return user;
    }
}
