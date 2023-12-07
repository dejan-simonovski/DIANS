package mk.com.kinmkd.kinmkd.service.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.PersistenceContext;
import mk.com.kinmkd.kinmkd.model.Location;
import mk.com.kinmkd.kinmkd.repository.LocationRepository;
import mk.com.kinmkd.kinmkd.service.LocationService;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @PersistenceContext
    private EntityManager entityManager;

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public void insertDataFromJsonFile(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = Files.readString(Path.of(jsonFilePath));
        System.out.println("JSON Content: " + jsonContent);

        Location[] locationsArray = objectMapper.readValue(Path.of(jsonFilePath).toFile(), Location[].class);

        for (Location location : locations) {
            System.out.println("Deserialized Location: " + location);
        }


       // locationRepository.saveAll(locations); <---- ova mora da raboti za da imame pocetna baza
    }
}
