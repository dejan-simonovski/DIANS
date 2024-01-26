package com.example.locationservice.service.impl;

import com.example.locationservice.model.Category;
import com.example.locationservice.model.Location;
import com.example.locationservice.model.exception.LocationNotFoundException;
import com.example.locationservice.repository.CategoryRepository;
import com.example.locationservice.repository.LocationRepository;
import com.example.locationservice.service.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
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
