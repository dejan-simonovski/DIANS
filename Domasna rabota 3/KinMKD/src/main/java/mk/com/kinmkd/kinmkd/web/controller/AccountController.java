package mk.com.kinmkd.kinmkd.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.com.kinmkd.kinmkd.model.User;
import mk.com.kinmkd.kinmkd.model.exception.EmailNotExistingException;
import mk.com.kinmkd.kinmkd.model.exception.EmailTakenException;
import mk.com.kinmkd.kinmkd.model.exception.IncorrectPasswordException;
import mk.com.kinmkd.kinmkd.model.exception.PasswordsNotMatchingException;
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
    public String getRegisterPage(Model model, HttpServletRequest req) {
        req.getSession().invalidate();
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
        } catch (PasswordsNotMatchingException | EmailTakenException e) {
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
                               HttpServletRequest req) {
        req.getSession().invalidate();
        model.addAttribute("hasError", false);

        model.addAttribute("body", "login");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", "login&signup.css");
        model.addAttribute("hasCssFile", true);
        return "master-layout";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            HttpServletRequest req,
                            Model model) {
        try {
            User user = userService.login(email, password);
            req.getSession().setAttribute("user", user);
        } catch (EmailNotExistingException | IncorrectPasswordException e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());

            model.addAttribute("body", "login");
            model.addAttribute("hasBody", true);
            model.addAttribute("cssFile", "login&signup.css");
            model.addAttribute("hasCssFile", true);
            return "master-layout";
        }
        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logoutUser(HttpServletRequest req) {
        req.getSession().invalidate();
        return "redirect:/home";
    }
}
