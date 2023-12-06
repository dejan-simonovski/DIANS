package mk.com.kinmkd.kinmkd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {
    @GetMapping("/home")
    public String getHomePage(Model model) {
        model.addAttribute("body", null);
        model.addAttribute("hasBody", false);
        model.addAttribute("cssFile", null);
        model.addAttribute("hasCssFile", false);
        return "master-layout";
    }

    @GetMapping("/about")
    public String getAboutPage(Model model) {
        model.addAttribute("body", "about");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", null);
        model.addAttribute("hasCssFile", false);
        return "master-layout";
    }
}
