package mk.com.kinmkd.kinmkd.service;

import mk.com.kinmkd.kinmkd.model.Review;
import mk.com.kinmkd.kinmkd.model.User;

public interface ReviewService {
    Review findByUserIdAndLocationId(Integer userId, Integer locationId);
    void deleteById(Integer id);
    void create(Integer rating,String comment,Integer userId,Integer locationId);
}
