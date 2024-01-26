package com.example.locationservice.service;



import com.example.locationservice.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findByName(String name);
}
