package mk.com.kinmkd.kinmkd.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.com.kinmkd.kinmkd.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {
    @GetMapping("/home")
    public String getHomePage(Model model,
                              HttpServletRequest req) {
        model.addAttribute("body", null);
        model.addAttribute("hasBody", false);
        model.addAttribute("cssFile", null);
        model.addAttribute("hasCssFile", false);
        model.addAttribute("user", (User) req.getSession().getAttribute("user"));
        return "master-layout";
    }

    @GetMapping("/about")
    public String getAboutPage(Model model,
                               HttpServletRequest req) {
        model.addAttribute("body", "about");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", null);
        model.addAttribute("hasCssFile", false);
        model.addAttribute("user", (User) req.getSession().getAttribute("user"));
        return "master-layout";
    }
}
