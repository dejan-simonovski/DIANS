package mk.com.kinmkd.kinmkd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("body", "signup");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", "login&signup.css");
        model.addAttribute("hasCssFile", true);
        return "master-layout";
    }

    @PostMapping("/register")
    public String registerUser() {
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("body", "login");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", "login&signup.css");
        model.addAttribute("hasCssFile", true);
        return "master-layout";
    }

    @PostMapping("/login")
    public String loginUser() {
        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logoutUser() {
        return "redirect:/home";
    }
}
