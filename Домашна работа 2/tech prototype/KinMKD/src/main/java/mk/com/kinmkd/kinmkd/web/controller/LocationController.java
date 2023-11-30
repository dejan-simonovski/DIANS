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
        return null;
    }

    @PostMapping("/search")
    public String searchLocations() {
        return null;
    }

    @GetMapping("/{id}")
    public String getLocationDetailsPage(@PathVariable Long id) {
        return null;
    }

    @GetMapping("/{id}/my-review")
    public String getLocationReviewPage(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/{id}/my-review/save")
    public String saveReviewForLocation(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/{id}/my-review/delete")
    public String deleteReviewForLocation(@PathVariable Long id) {
        return null;
    }
}
