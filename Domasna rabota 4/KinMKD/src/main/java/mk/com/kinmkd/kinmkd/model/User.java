package mk.com.kinmkd.kinmkd.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    public User(String email, String password) {
        this.email = email;
        setPassword(password);
        reviews = new ArrayList<>();
    }
}
