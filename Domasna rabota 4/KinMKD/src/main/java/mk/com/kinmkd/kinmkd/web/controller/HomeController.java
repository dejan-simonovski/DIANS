package mk.com.kinmkd.kinmkd.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.com.kinmkd.kinmkd.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller handling home and about page-related operations.
 */
@Controller
public class HomeController {

    /**
     * Displays the home page.
     * @param model - the model for the view
     * @return the master layout view for the home page
     */
    @GetMapping("/home")
    public String getHomePage(Model model) {
        model.addAttribute("body", null);
        model.addAttribute("hasBody", false);
        model.addAttribute("cssFile", null);
        model.addAttribute("hasCssFile", false);
        return "master-layout";
    }

    /**
     * Redirects to the home page.
     * @return redirects to the "/home" endpoint
     */
    @GetMapping
    public String getHomeRedirect(){
        return "redirect:/home";
    }

    /**
     * Displays the about page.
     * @param model - the model for the view
     * @return the master layout view for the about page
     */
    @GetMapping("/about")
    public String getAboutPage(Model model) {
        model.addAttribute("body", "about");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", null);
        model.addAttribute("hasCssFile", false);
        return "master-layout";
    }
}
