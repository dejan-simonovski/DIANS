package mk.com.kinmkd.kinmkd.service;

import mk.com.kinmkd.kinmkd.model.Location;
import java.io.IOException;
import java.util.List;

public interface LocationService {
    void insertDataFromJsonFile(String jsonFilePath) throws IOException;
    Location findById(Integer id);
    List<Location> performSearch(String categoryName, String text);
}
