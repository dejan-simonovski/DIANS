package com.example.locationservice.repository;


import com.example.locationservice.model.Category;
import com.example.locationservice.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> findAllByCategoryIdAndNameContainsIgnoreCaseOrCategoryIdAndNameENContainsIgnoreCase(Category category1, String text1, Category category2, String text2);
    List<Location> findAllByNameENContainsIgnoreCaseOrNameContainsIgnoreCase(String text,String text2);
    Location findByNameENOrNameAndAndCategoryId(String text1,String text2,String categoryId);
    List<Location> findAllByCategoryId(Category category);
}
