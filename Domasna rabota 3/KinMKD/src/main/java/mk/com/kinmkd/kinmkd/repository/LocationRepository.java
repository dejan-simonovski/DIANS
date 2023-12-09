package mk.com.kinmkd.kinmkd.repository;

import mk.com.kinmkd.kinmkd.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mk.com.kinmkd.kinmkd.model.Location;

import java.util.List;
import java.util.Optional;


public interface LocationRepository extends JpaRepository<Location, Integer> {

    List<Location> findByNameENContainingIgnoreCaseOrNameContainingIgnoreCase(String text,String text2);
    Location findByNameENOrNameAndAndCategoryId(String text1,String text2,String categoryId);
    //Optional<Location> findById(Integer id);
    List<Location> findByCategoryId(String categoryId);
}
