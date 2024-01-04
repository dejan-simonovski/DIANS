package mk.com.kinmkd.kinmkd.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.com.kinmkd.kinmkd.model.User;
import mk.com.kinmkd.kinmkd.model.exception.*;
import mk.com.kinmkd.kinmkd.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    private final UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("hasError", false);

        model.addAttribute("body", "signup");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", "login&signup.css");
        model.addAttribute("hasCssFile", true);
        return "master-layout";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String repeatPassword,
                               Model model) {
        try {
            userService.register(email, password, repeatPassword);
        } catch (PasswordsNotMatchingException | EmailTakenException | PasswordWeakException e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());

            model.addAttribute("body", "signup");
            model.addAttribute("hasBody", true);
            model.addAttribute("cssFile", "login&signup.css");
            model.addAttribute("hasCssFile", true);
            return "master-layout";
        }
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model,
                               @RequestParam(required = false) String error) {
        model.addAttribute("hasError", error != null);
        model.addAttribute("error", error);

        model.addAttribute("body", "login");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", "login&signup.css");
        model.addAttribute("hasCssFile", true);
        return "master-layout";
    }
}
