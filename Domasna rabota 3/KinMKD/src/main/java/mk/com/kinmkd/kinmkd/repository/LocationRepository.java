package mk.com.kinmkd.kinmkd.repository;

import mk.com.kinmkd.kinmkd.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mk.com.kinmkd.kinmkd.model.Location;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    Location findLocationByName_enOrNameAndCategoryId(String text,Integer categoryId);
    List<Location> findByName_enContainingIgnoreCaseOrNameContainingIgnoreCase(String text);

    Optional<Location> findById(Integer id);
    List<Location> findByCategoryId(Integer categoryId);
}
