package mk.com.kinmkd.kinmkd.repository;

import mk.com.kinmkd.kinmkd.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import mk.com.kinmkd.kinmkd.model.Location;
import java.util.List;


public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> findAllByCategoryIdAndNameContainsIgnoreCaseOrCategoryIdAndNameENContainsIgnoreCase(Category category1, String text1, Category category2, String text2);
    List<Location> findAllByNameENContainsIgnoreCaseOrNameContainsIgnoreCase(String text,String text2);
    Location findByNameENOrNameAndAndCategoryId(String text1,String text2,String categoryId);
    List<Location> findAllByCategoryId(Category category);
}
