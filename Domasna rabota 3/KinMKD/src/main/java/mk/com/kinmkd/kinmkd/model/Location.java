package mk.com.kinmkd.kinmkd.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "locations")
public class Location {
    @Id
    private int id;
    private double lat;
    private double lon;
    private String name;
    private String nameEng;
    @Enumerated(EnumType.STRING)
    private Category categoryId;
}
