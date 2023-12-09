package mk.com.kinmkd.kinmkd.service.Impl;

import mk.com.kinmkd.kinmkd.model.Location;
import mk.com.kinmkd.kinmkd.model.Review;
import mk.com.kinmkd.kinmkd.model.User;
import mk.com.kinmkd.kinmkd.repository.LocationRepository;
import mk.com.kinmkd.kinmkd.repository.ReviewRepository;
import mk.com.kinmkd.kinmkd.repository.UserRepository;
import mk.com.kinmkd.kinmkd.service.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, LocationRepository locationRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;

        this.locationRepository = locationRepository;
    }

    @Override
    public Review findByUserIdAndLocationId(Integer userId, Integer locationId) {
        return reviewRepository.findByUserIdAndLocationId(userId,locationId);
    }

    @Override
    public void deleteById(Integer id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public void create(Integer rating, String comment, Integer userId, Integer locationId) {
        User user=userRepository.findById(userId).get();
        Location location=locationRepository.findById(locationId).get();
        Review review=new Review(rating,comment,user,location);
        reviewRepository.save(review);
    }
}
