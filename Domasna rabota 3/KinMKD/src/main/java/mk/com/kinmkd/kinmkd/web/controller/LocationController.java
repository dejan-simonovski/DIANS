package mk.com.kinmkd.kinmkd.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.com.kinmkd.kinmkd.model.Category;
import mk.com.kinmkd.kinmkd.model.Location;
import mk.com.kinmkd.kinmkd.model.User;
import mk.com.kinmkd.kinmkd.service.CategoryService;
import mk.com.kinmkd.kinmkd.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/location")
@AllArgsConstructor
public class LocationController {
    private final LocationService locationService;
    private final CategoryService categoryService;

    @GetMapping("/search")
    public String getLocationSearchPage(Model model, HttpServletRequest req) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("hasBeenSearched", false);

        model.addAttribute("body", "search");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", "search-style.css");
        model.addAttribute("hasCssFile", true);
        model.addAttribute("user", (User) req.getSession().getAttribute("user"));
        return "master-layout";
    }

    @PostMapping("/search")
    public String searchLocations(Model model,
                                  @RequestParam String nameKeyword,
                                  @RequestParam String categoryName,
                                  HttpServletRequest req) {
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
        model.addAttribute("user", (User) req.getSession().getAttribute("user"));
        return "master-layout";
    }

    @GetMapping("/{id}")
    public String getLocationDetailsPage(@PathVariable Integer id,
                                         Model model,
                                         HttpServletRequest req) {
        Optional<Location> location = locationService.findById(id);
        if (location.isEmpty()) {
            return "redirect:/location/search";
        }
        model.addAttribute("location", location.get());

        model.addAttribute("body", "details");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", "details.css");
        model.addAttribute("hasCssFile", true);
        model.addAttribute("user", (User) req.getSession().getAttribute("user"));
        return "master-layout";
    }

    @GetMapping("/{id}/my-review")
    public String getLocationReviewPage(@PathVariable Long id,
                                        Model model,
                                        HttpServletRequest req) {
        model.addAttribute("body", "myReview");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", "review.css");
        model.addAttribute("hasCssFile", true);
        model.addAttribute("user", (User) req.getSession().getAttribute("user"));
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
