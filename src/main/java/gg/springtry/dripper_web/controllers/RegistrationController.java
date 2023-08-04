package gg.springtry.dripper_web.controllers;

import gg.springtry.dripper_web.models.User;
import gg.springtry.dripper_web.services.UserService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registration(Model model) {
        if (userService.isLogged()) {
            return "redirect:/";
        }
        model.addAttribute("userForm", new User());
        return "registration-page";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute("userForm") User userForm, Model model) {

        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration-page";
        }

        try {
            userService.saveUser(userForm);
        } catch (ConstraintViolationException e) {
            List<String> errors = new ArrayList<>();
            if (e.getConstraintViolations() == null) { // if no constraint violations, then it's a unique constraint violation
                errors.add(e.getMessage());
            } else {
                for (ConstraintViolation<?> t : e.getConstraintViolations()) {
                    errors.add(t.getMessage());
                }
            }
            model.addAttribute("errors", errors);
            return "registration-page";
        }

        return "redirect:/login";
    }
}
