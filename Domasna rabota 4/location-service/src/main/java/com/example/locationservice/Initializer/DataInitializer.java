package com.example.locationservice.Initializer;

import com.example.locationservice.service.LocationService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class DataInitializer {

    private final LocationService locationService;

    public DataInitializer(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostConstruct
    public void init() {
        try {
            String jsonFilePath = "src\\main\\resources\\locations.json";
            File file = new File(jsonFilePath);
            if (!file.exists()) {
                // If not found, use the Docker image location
                jsonFilePath = "/app/locations.json";
            }

            locationService.insertDataFromJsonFile(jsonFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
