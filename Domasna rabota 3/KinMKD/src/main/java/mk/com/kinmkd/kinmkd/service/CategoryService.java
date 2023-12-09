package mk.com.kinmkd.kinmkd.service;

import mk.com.kinmkd.kinmkd.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findByName(String name);
}
