package mk.com.kinmkd.kinmkd.service.Impl;

import lombok.AllArgsConstructor;
import mk.com.kinmkd.kinmkd.model.User;
import mk.com.kinmkd.kinmkd.model.exception.*;
import mk.com.kinmkd.kinmkd.repository.UserRepository;
import mk.com.kinmkd.kinmkd.service.UserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public User register(String email, String password, String repeatPassword) {

        validatePassword(password, repeatPassword);

        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailTakenException(email);
        }
        User user = new User(email, password);
        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotExistingException(email));

        if (!user.verifyPassword(password)) {
            throw new IncorrectPasswordException();
        }
        return user;
    }

    private void validatePassword(String password, String repeatPassword) {
        if (!password.equals(repeatPassword)) {
            throw new PasswordsNotMatchingException();
        }
        if (!(password.length() >= 6 && password.matches(".*[0-9]+.*") && password.matches(".*[a-zA-Z]+.*"))) {
            throw new PasswordWeakException();
        }
    }
}
