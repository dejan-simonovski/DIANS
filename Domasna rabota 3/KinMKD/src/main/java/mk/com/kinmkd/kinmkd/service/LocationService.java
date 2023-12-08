package mk.com.kinmkd.kinmkd.service;

import mk.com.kinmkd.kinmkd.model.Category;
import mk.com.kinmkd.kinmkd.model.Location;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

public interface LocationService {

    void insertDataFromJsonFile(String jsonFilePath) throws IOException;
    List<Location> findAll();

    Location findById(Integer id);
    Location findByNameAndCategory(String name,Integer categoryId);

    List<Location> findByLikeName(String name);
    List<Location> findByCategory(Integer categoryId);



}
