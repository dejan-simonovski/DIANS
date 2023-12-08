package mk.com.kinmkd.kinmkd.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import mk.com.kinmkd.kinmkd.model.Category;
import mk.com.kinmkd.kinmkd.model.Location;
import mk.com.kinmkd.kinmkd.repository.LocationRepository;
import mk.com.kinmkd.kinmkd.service.LocationService;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public void insertDataFromJsonFile(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = Files.readString(Path.of(jsonFilePath));

        try {
            Location[] locationsArray = objectMapper.readValue(jsonContent, Location[].class);

            for (Location location : locationsArray) {
                Location newLocation = new Location(location.getId(),
                        location.getLat(),
                        location.getLon(),
                        location.getName(),
                        location.getName_en(),
                        location.getCategoryId());

                this.locationRepository.save(newLocation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location findById(Integer id) {
        return locationRepository.findById(id).get();
    }

    @Override
    public Location findByNameAndCategory(String name, Integer categoryId) {
        return locationRepository.findLocationByName_enOrNameAndCategoryId(name, categoryId);
    }

    @Override
    public List<Location> findByLikeName(String name) {
        return locationRepository.findByName_enContainingIgnoreCaseOrNameContainingIgnoreCase(name);
    }

    @Override
    public List<Location> findByCategory(Integer categoryId) {
        return locationRepository.findByCategoryId(categoryId);
    }
}
