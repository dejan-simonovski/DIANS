package mk.com.kinmkd.kinmkd.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import mk.com.kinmkd.kinmkd.model.Category;
import mk.com.kinmkd.kinmkd.model.Location;
import mk.com.kinmkd.kinmkd.model.exception.LocationNotFoundException;
import mk.com.kinmkd.kinmkd.repository.CategoryRepository;
import mk.com.kinmkd.kinmkd.repository.LocationRepository;
import mk.com.kinmkd.kinmkd.service.LocationService;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;

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
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location findById(Integer id) {
        return locationRepository.findById(id)
                .orElseThrow(LocationNotFoundException::new);
    }

    @Override
    public Location findByNameAndCategory(String name, String category) {
        return locationRepository.findByNameENOrNameAndAndCategoryId(name,name,category);
    }

    @Override
    public List<Location> findByLikeName(String text1, String text2) {
        return locationRepository.findAllByNameENContainsIgnoreCaseOrNameContainsIgnoreCase(text1,text2);
    }

    @Override
    public List<Location> findByCategory(String category) {
        Optional<Category> cHolder = categoryRepository.findByName(category);
        Category c = cHolder.get();
        return locationRepository.findAllByCategoryId(c);
    }

    @Override
    public List<Location> findByNameLikeAndCategory(String categoryName, String text) {
        Optional<Category> cHolder = categoryRepository.findByName(categoryName);
        Category category = cHolder.get();
        return locationRepository.findAllByCategoryIdAndNameContainsIgnoreCaseOrCategoryIdAndNameENContainsIgnoreCase(
                category, text, category, text
        );
    }

    @Override
    public List<Location> performSearch(String categoryName, String text) {
        if (categoryName.equals("NONE") && checkIfEmpty(text)) {
            return findAll();
        } else if (categoryName.equals("NONE") && !checkIfEmpty(text)) {
            return findByLikeName(text, text);
        } else if (!categoryName.equals("NONE") && checkIfEmpty(text)) {
            return findByCategory(categoryName);
        } else {
            return findByNameLikeAndCategory(
                    categoryName, text
            );
        }
    }

    private boolean checkIfEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
