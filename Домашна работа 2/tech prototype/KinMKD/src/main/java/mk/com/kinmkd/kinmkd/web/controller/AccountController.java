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
        return null;
    }

    @PostMapping("/register")
    public String registerUser() {
        return null;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return null;
    }

    @PostMapping("/login")
    public String loginUser() {
        return null;
    }

    @PostMapping("/logout")
    public String logoutUser() {
        return null;
    }
}
