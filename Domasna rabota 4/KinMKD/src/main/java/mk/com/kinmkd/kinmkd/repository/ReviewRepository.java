package mk.com.kinmkd.kinmkd.repository;

import mk.com.kinmkd.kinmkd.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
     Optional<Review> findByUserIdAndLocationId(Integer userId, Integer locationId);
}
