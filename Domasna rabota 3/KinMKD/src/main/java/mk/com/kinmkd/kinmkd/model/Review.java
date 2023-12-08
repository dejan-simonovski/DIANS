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
    private Integer Id;
    private Integer rating;
    private String comment;
    @ManyToOne
    private User user;
    @ManyToOne
    private Location location;


    public Review(Integer rating, String comment, User user, Location location) {
        this.rating = rating;
        this.comment = comment;
        this.user = user;
        this.location = location;
    }
}
