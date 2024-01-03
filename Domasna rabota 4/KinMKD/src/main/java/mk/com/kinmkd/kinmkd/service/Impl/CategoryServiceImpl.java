package mk.com.kinmkd.kinmkd.service.Impl;

import lombok.AllArgsConstructor;
import mk.com.kinmkd.kinmkd.model.Category;
import mk.com.kinmkd.kinmkd.repository.CategoryRepository;
import mk.com.kinmkd.kinmkd.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
