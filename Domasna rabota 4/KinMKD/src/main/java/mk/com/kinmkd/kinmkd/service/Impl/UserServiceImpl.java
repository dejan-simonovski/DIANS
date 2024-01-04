package mk.com.kinmkd.kinmkd.service.Impl;

import lombok.AllArgsConstructor;
import mk.com.kinmkd.kinmkd.model.User;
import mk.com.kinmkd.kinmkd.model.exception.*;
import mk.com.kinmkd.kinmkd.repository.UserRepository;
import mk.com.kinmkd.kinmkd.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(String email, String password, String repeatPassword) {

        validatePassword(password, repeatPassword);

        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailTakenException(email);
        }
        User user = new User(email, passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new EmailNotExistingException(email));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Email %s doesn't exist!", username)));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
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
