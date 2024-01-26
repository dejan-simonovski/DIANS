package mk.com.kinmkd.kinmkd.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import mk.com.kinmkd.kinmkd.model.Category;
import mk.com.kinmkd.kinmkd.model.Location;
import mk.com.kinmkd.kinmkd.model.exception.LocationNotFoundException;
import mk.com.kinmkd.kinmkd.repository.CategoryRepository;
import mk.com.kinmkd.kinmkd.repository.LocationRepository;
import mk.com.kinmkd.kinmkd.service.LocationService;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final RestTemplate restTemplate;

    public void insertDataFromJsonFile(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = Files.readString(Path.of(jsonFilePath));

        try {
            Location[] locationsArray = objectMapper.readValue(jsonContent, Location[].class);

            Arrays.stream(locationsArray)
                    .map(location -> new Location(
                            location.getId(),
                            location.getLat(),
                            location.getLon(),
                            location.getName(),
                            location.getNameEN(),
                            location.getCategoryId())
                    )
                    .forEach(locationRepository::save);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Location findById(Integer id) {
        return locationRepository.findById(id)
                .orElseThrow(LocationNotFoundException::new);
    }

    @Override
    public List<Location> performSearch(String categoryName, String text) {
        return Arrays.asList(Objects.requireNonNull(
                restTemplate.exchange(String.format("http://localhost:8761/eureka?nameKeyword=%s&categoryName=%s",text,categoryName),
                        HttpMethod.POST,
                        null,
                        Location[].class
                ).getBody()));
    }
}
