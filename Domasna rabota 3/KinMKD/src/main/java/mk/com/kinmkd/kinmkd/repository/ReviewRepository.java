package mk.com.kinmkd.kinmkd.repository;

import mk.com.kinmkd.kinmkd.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
     Review findByUserIdAndLocationId(Integer userId,Integer locationId);
     void deleteById(Integer id);
}
