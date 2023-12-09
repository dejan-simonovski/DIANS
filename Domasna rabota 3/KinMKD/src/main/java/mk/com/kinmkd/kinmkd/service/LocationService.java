package mk.com.kinmkd.kinmkd.service;

import mk.com.kinmkd.kinmkd.model.Category;
import mk.com.kinmkd.kinmkd.model.Location;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface LocationService {

    void insertDataFromJsonFile(String jsonFilePath) throws IOException;
    List<Location> findAll();

    Optional<Location> findById(Integer id);
    Location findByNameAndCategory(String name,String category);

    List<Location> findByLikeName(String text1,String text2);
    List<Location> findByCategory(String category);



}
