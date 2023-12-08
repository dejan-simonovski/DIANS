package mk.com.kinmkd.kinmkd.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
