package mk.com.kinmkd.kinmkd.service;

import mk.com.kinmkd.kinmkd.model.Location;
import java.io.IOException;
import java.util.List;

public interface LocationService {

    void insertDataFromJsonFile(String jsonFilePath) throws IOException;
    List<Location> findAll();

    Location findById(Integer id);
    Location findByNameAndCategory(String name,String category);

    List<Location> findByLikeName(String text1,String text2);
    List<Location> findByCategory(String category);

    List<Location> findByNameLikeAndCategory(String categoryName, String text);

    List<Location> performSearch(String categoryName, String text);
}
