package mk.com.kinmkd.kinmkd.model;

import jakarta.persistence.OneToMany;

import java.util.List;

public enum Category {
    NONE,
    MONUMENT,
    BUILDING,
    RUINS,
    MUSEUM;
}
