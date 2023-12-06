package mk.com.kinmkd.kinmkd.model;

public enum Category {
    MONUMENT,
    BUILDING,
    RUINS,
    MUSEUM;

    public int getValue() {
        return ordinal() + 1;
    }
}
