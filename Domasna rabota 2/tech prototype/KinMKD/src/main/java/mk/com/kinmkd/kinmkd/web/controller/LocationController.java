package mk.com.kinmkd.kinmkd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/location")
public class LocationController {
    @GetMapping("/search")
    public String getLocationSearchPage() {
        return "search";
    }

    @PostMapping("/search")
    public String searchLocations() {
        return "search";
    }

    @GetMapping("/{id}")
    public String getLocationDetailsPage(@PathVariable Long id) {
        return "details";
    }

    @GetMapping("/{id}/my-review")
    public String getLocationReviewPage(@PathVariable Long id) {
        return "myReview";
    }

    @PostMapping("/{id}/my-review/save")
    public String saveReviewForLocation(@PathVariable Long id) {
        return String.format("redirect:/location/%d", id);
    }

    @PostMapping("/{id}/my-review/delete")
    public String deleteReviewForLocation(@PathVariable Long id) {
        return String.format("redirect:/location/%d", id);
    }
}
