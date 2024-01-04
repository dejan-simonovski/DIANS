package mk.com.kinmkd.kinmkd.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.com.kinmkd.kinmkd.model.Category;
import mk.com.kinmkd.kinmkd.model.Location;
import mk.com.kinmkd.kinmkd.model.Review;
import mk.com.kinmkd.kinmkd.model.User;
import mk.com.kinmkd.kinmkd.model.exception.LocationNotFoundException;
import mk.com.kinmkd.kinmkd.service.CategoryService;
import mk.com.kinmkd.kinmkd.service.LocationService;
import mk.com.kinmkd.kinmkd.service.ReviewService;
import mk.com.kinmkd.kinmkd.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller handling location-related operations.
 */

@Controller
@RequestMapping("/location")
@AllArgsConstructor
public class LocationController {
    private final LocationService locationService;
    private final CategoryService categoryService;
    private final ReviewService reviewService;
    private final UserService userService;

    /**
     * Displays the location search page.
     * @param model - the model for the view
     * @return the master layout view for the location search page
     */
    @GetMapping("/search")
    public String getLocationSearchPage(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("hasBeenSearched", false);

        model.addAttribute("body", "search");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", "search-style.css");
        model.addAttribute("hasCssFile", true);
        return "master-layout";
    }

    /**
     * Handles the search for locations based on given parameters.
     * @param model - the model for the view
     * @param nameKeyword - keyword for location name search
     * @param categoryName - selected category for filtering
     * @return the master layout view for the location search page with search results
     */
    @PostMapping("/search")
    public String searchLocations(Model model,
                                  @RequestParam String nameKeyword,
                                  @RequestParam String categoryName) {
        List<Location> resultLocations = locationService.performSearch(categoryName, nameKeyword);
        model.addAttribute("resultLocations", resultLocations);
        model.addAttribute("keyword", nameKeyword);
        model.addAttribute("categoryName", categoryName);

        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("hasBeenSearched", true);

        model.addAttribute("body", "search");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", "search-style.css");
        model.addAttribute("hasCssFile", true);
        return "master-layout";
    }

    /**
     * Displays the details page for a specific location.
     * @param id - the ID of the location to display details for
     * @param model - the model for the view
     * @return the master layout view for the location details page
     */
    @GetMapping("/{id}")
    public String getLocationDetailsPage(@PathVariable Integer id,
                                         Model model) {
        try {
            Location location = locationService.findById(id);
            model.addAttribute("location", location);
        } catch (LocationNotFoundException e) {
            return "redirect:/location/search";
        }

        model.addAttribute("body", "details");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", "details.css");
        model.addAttribute("hasCssFile", true);
        return "master-layout";
    }

    /**
     * Displays the review page for a specific location.
     * @param id - the ID of the location to display reviews for
     * @param model - the model for the view
     * @param req - the HTTP servlet request
     * @return the master layout view for the location review page
     */
    @GetMapping("/{id}/my-review")
    public String getLocationReviewPage(@PathVariable Integer id,
                                        Model model,
                                        HttpServletRequest req) {
        try {
            Location location = locationService.findById(id);
            model.addAttribute("location", location);

            String email = req.getRemoteUser();
            Integer userId = userService.findByEmail(email).getId();
            Optional<Review> review = reviewService.findByUserIdAndLocationId(userId, id);

            if (review.isEmpty()) {
                model.addAttribute("hasReview", false);
                model.addAttribute("review", null);
            } else {
                model.addAttribute("hasReview", true);
                model.addAttribute("review", review.get());
            }
            model.addAttribute("destPath", String.format("/location/%d/my-review/save", id));
        } catch (LocationNotFoundException e) {
            return "redirect:/location/search";
        }

        model.addAttribute("body", "myReview");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", "review.css");
        model.addAttribute("hasCssFile", true);
        return "master-layout";
    }

    /**
     * Saves a review for a specific location.
     * @param id - the ID of the location to save the review for
     * @param comment - the user's comment for the review
     * @param rating - the user's rating for the location
     * @param req - the HTTP servlet request
     * @return redirects to the location details page after saving the review
     */
    @PostMapping("/{id}/my-review/save")
    public String saveReviewForLocation(@PathVariable Integer id,
                                        @RequestParam String comment,
                                        @RequestParam Integer rating,
                                        HttpServletRequest req) {
        try {
            reviewService.create(
                    rating,
                    comment,
                    userService.findByEmail(req.getRemoteUser()).getId(),
                    id
            );
        } catch (LocationNotFoundException e) {
            return "redirect:/location/search";
        }
        return String.format("redirect:/location/%d", id);
    }

    /**
     * Deletes a review for a specific location.
     * @param id - the ID of the location to delete the review for
     * @param req - the HTTP servlet request
     * @return redirects to the location details page after deleting the review
     */
    @PostMapping("/{id}/my-review/delete")
    public String deleteReviewForLocation(@PathVariable Integer id,
                                          HttpServletRequest req) {
        reviewService.deleteByUserIdAndLocationId(
                userService.findByEmail(req.getRemoteUser()).getId(),
                id
        );
        return String.format("redirect:/location/%d", id);
    }
}
