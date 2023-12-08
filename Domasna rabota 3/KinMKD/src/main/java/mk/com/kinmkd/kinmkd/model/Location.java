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
    private Integer id;

    private Double lat;
    private Double lon;

    private String name;
    private String name_en;

    @Enumerated(EnumType.STRING)
    private Category categoryId;

    public Location(Integer id, Double lat, Double lon, String name, String name_en, Category categoryId) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.name = name;
        this.name_en = name_en;
        this.categoryId = categoryId;
    }

    @OneToMany
    private List<Review> reviews;
}
