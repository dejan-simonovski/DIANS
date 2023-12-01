package mk.com.kinmkd.kinmkd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/location")
public class LocationController {
    @GetMapping("/search")
    public String getLocationSearchPage(Model model) {
        model.addAttribute("body", "search");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", "search-style.css");
        model.addAttribute("hasCssFile", true);
        return "master-layout";
    }

    @PostMapping("/search")
    public String searchLocations(Model model) {
        model.addAttribute("body", "search");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", "search-style.css");
        model.addAttribute("hasCssFile", true);
        return "master-layout";
    }

    @GetMapping("/{id}")
    public String getLocationDetailsPage(@PathVariable Long id,
                                         Model model) {
        model.addAttribute("body", "details");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", "details.css");
        model.addAttribute("hasCssFile", true);
        return "master-layout";
    }

    @GetMapping("/{id}/my-review")
    public String getLocationReviewPage(@PathVariable Long id,
                                        Model model) {
        model.addAttribute("body", "myReview");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", "review.css");
        model.addAttribute("hasCssFile", true);
        return "master-layout";
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
