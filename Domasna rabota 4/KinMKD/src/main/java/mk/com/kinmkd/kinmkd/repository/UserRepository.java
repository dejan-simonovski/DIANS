package mk.com.kinmkd.kinmkd.repository;

import mk.com.kinmkd.kinmkd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findById(Integer id);
    Optional<User> findByEmail(String email);
}
