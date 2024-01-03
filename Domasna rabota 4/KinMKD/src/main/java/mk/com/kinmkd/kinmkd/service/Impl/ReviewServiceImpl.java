package mk.com.kinmkd.kinmkd.service.Impl;

import lombok.AllArgsConstructor;
import mk.com.kinmkd.kinmkd.model.Location;
import mk.com.kinmkd.kinmkd.model.Review;
import mk.com.kinmkd.kinmkd.model.User;
import mk.com.kinmkd.kinmkd.model.exception.LocationNotFoundException;
import mk.com.kinmkd.kinmkd.model.exception.UserNotFoundException;
import mk.com.kinmkd.kinmkd.repository.LocationRepository;
import mk.com.kinmkd.kinmkd.repository.ReviewRepository;
import mk.com.kinmkd.kinmkd.repository.UserRepository;
import mk.com.kinmkd.kinmkd.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    @Override
    public Optional<Review> findByUserIdAndLocationId(Integer userId, Integer locationId) {
        return reviewRepository.findByUserIdAndLocationId(userId,locationId);
    }

    @Override
    public Review create(Integer rating, String comment, Integer userId, Integer locationId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        Location location = locationRepository.findById(locationId)
                .orElseThrow(LocationNotFoundException::new);

        Optional<Review> revContainer = reviewRepository.findByUserIdAndLocationId(userId, locationId);
        Review review;
        if (revContainer.isPresent()) {
            review = revContainer.get();
            review.setComment(comment);
            review.setRating(rating);
        } else {
            review = new Review(rating,comment,user,location);
        }
        return reviewRepository.save(review);
    }

    @Override
    public void deleteByUserIdAndLocationId(Integer userId, Integer locationId) {
        reviewRepository.findByUserIdAndLocationId(userId, locationId)
                .ifPresent(reviewRepository::delete);
    }
}
