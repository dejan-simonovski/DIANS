package com.example.locationservice.service;



import com.example.locationservice.model.Location;

import java.io.IOException;
import java.util.List;

public interface LocationService {
    void insertDataFromJsonFile(String jsonFilePath) throws IOException;
    List<Location> findAll();
    List<Location> findByLikeName(String text1,String text2);
    List<Location> findByCategory(String category);
    List<Location> findByNameLikeAndCategory(String categoryName, String text);
    List<Location> performSearch(String categoryName, String text);
}
