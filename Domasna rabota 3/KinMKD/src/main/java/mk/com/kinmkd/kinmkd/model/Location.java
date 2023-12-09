package mk.com.kinmkd.kinmkd.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double lat;
    private Double lon;

    private String name;
    private String nameEN;

    @Enumerated(EnumType.STRING)
    private Category categoryId;

    public Location(Integer id, Double lat, Double lon, String name, String nameEN, Category categoryId) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.name = name;
        this.nameEN = nameEN;
        this.categoryId = categoryId;
    }

    @OneToMany(mappedBy = "location")
    private List<Review> reviews;
}
