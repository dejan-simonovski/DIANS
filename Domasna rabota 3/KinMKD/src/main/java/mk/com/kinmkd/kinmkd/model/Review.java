package mk.com.kinmkd.kinmkd.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private Integer rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id") // Specify the correct column name
    private User user;

    @ManyToOne
    @JoinColumn(name = "location_id") // Specify the correct column name
    private Location location;
}
