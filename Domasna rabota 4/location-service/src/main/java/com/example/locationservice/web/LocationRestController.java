package com.example.locationservice.web;

import com.example.locationservice.model.Location;
import com.example.locationservice.service.CategoryService;
import com.example.locationservice.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class LocationRestController {
    private final LocationService locationService;

    @PostMapping("/")
    public List<Location> searchLocations(@RequestParam String nameKeyword,
                                  @RequestParam String categoryName) {
        return locationService.performSearch(categoryName, nameKeyword);
    }
}
