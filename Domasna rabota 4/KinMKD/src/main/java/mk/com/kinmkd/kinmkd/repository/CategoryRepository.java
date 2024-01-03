package mk.com.kinmkd.kinmkd.repository;

import mk.com.kinmkd.kinmkd.model.Category;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {
    public List<Category> findAll() {
        return Arrays.asList(Category.NONE, Category.MONUMENT, Category.BUILDING, Category.RUINS, Category.MUSEUM);
    }

    public Optional<Category> findByName(String name) {
        return findAll().stream()
                .filter(c -> c.name().equals(name))
                .findFirst();
    }
}
