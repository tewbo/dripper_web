package gg.springtry.dripper_web.controllers;


import gg.springtry.dripper_web.models.User;
import gg.springtry.dripper_web.repo.UserRepository;
import gg.springtry.dripper_web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class MainController {
    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("userId", 152L);
        return "main";
    }

    @GetMapping("/user-page")
    public String userPage(Model model) {
        return "user-page";
    }

    @GetMapping("/user/delete")
    public String userDelete(@RequestParam Long userId, Model model) {
        userService.deleteUser(userId);
        return "redirect:/";
    }

    @GetMapping("top-users")
    public String topUsers(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "top-users";
    }

}