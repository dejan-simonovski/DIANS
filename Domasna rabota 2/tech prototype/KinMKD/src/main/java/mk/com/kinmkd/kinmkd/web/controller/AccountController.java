package mk.com.kinmkd.kinmkd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
    @GetMapping("/register")
    public String getRegisterPage() {
        return "signup";
    }

    @PostMapping("/register")
    public String registerUser() {
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
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
