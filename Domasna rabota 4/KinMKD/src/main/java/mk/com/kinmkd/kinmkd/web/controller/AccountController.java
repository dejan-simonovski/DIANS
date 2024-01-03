package mk.com.kinmkd.kinmkd.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.com.kinmkd.kinmkd.model.User;
import mk.com.kinmkd.kinmkd.model.exception.*;
import mk.com.kinmkd.kinmkd.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller handling user account-related operations.
 */
@Controller
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    private final UserService userService;

    /**
     * Displays the registration page.
     * @param model - the model for the view
     * @param req - the HTTP servlet request
     * @return the master layout view for registration
     */
    @GetMapping("/register")
    public String getRegisterPage(Model model, HttpServletRequest req) {
        req.getSession().invalidate();
        model.addAttribute("hasError", false);

        model.addAttribute("body", "signup");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", "login&signup.css");
        model.addAttribute("hasCssFile", true);
        model.addAttribute("user", null);
        return "master-layout";
    }


    /**
     * Handles the registration of a new user.
     * @param email - the user's email
     * @param password - the user's password
     * @param repeatPassword - repeated password for confirmation
     * @param model - the model for the view
     * @return redirects to the home page if registration is successful, else returns to the registration page with an error message
     */
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
            model.addAttribute("user", null);
            return "master-layout";
        }
        return "redirect:/home";
    }

    /**
     * Displays the login page.
     * @param model - the model for the view
     * @param req - the HTTP servlet request
     * @return the master layout view for the login page
     */
    @GetMapping("/login")
    public String getLoginPage(Model model,
                               HttpServletRequest req) {
        req.getSession().invalidate();
        model.addAttribute("hasError", false);

        model.addAttribute("body", "login");
        model.addAttribute("hasBody", true);
        model.addAttribute("cssFile", "login&signup.css");
        model.addAttribute("hasCssFile", true);
        model.addAttribute("user", null);
        return "master-layout";
    }

    /**
     * Handles the login of a user.
     * @param email - the user's email
     * @param password - the user's password
     * @param req - the HTTP servlet request
     * @param model - the model for the view
     * @return redirects to the home page if login is successful, else returns to the login page with an error message
     */
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
            model.addAttribute("user", null);
            return "master-layout";
        }
        return "redirect:/home";
    }

    /**
     * Handles the logout of a user.
     * @param req - the HTTP servlet request
     * @return redirects to the home page after invalidating the session
     */
    @PostMapping("/logout")
    public String logoutUser(HttpServletRequest req) {
        req.getSession().invalidate();
        return "redirect:/home";
    }
}
