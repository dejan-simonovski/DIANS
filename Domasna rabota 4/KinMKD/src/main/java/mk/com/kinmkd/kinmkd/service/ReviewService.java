package mk.com.kinmkd.kinmkd.service;

import mk.com.kinmkd.kinmkd.model.Review;
import java.util.Optional;

public interface ReviewService {
    Optional<Review> findByUserIdAndLocationId(Integer userId, Integer locationId);
    void deleteByUserIdAndLocationId(Integer userId, Integer locationId);
    Review create(Integer rating,String comment,Integer userId,Integer locationId);
}
