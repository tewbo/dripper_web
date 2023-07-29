package gg.springtry.dripper_web.controllers;


import gg.springtry.dripper_web.models.User;
import gg.springtry.dripper_web.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class MainController {
    private final UserRepository userRepository;

    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("userId", 152L);
        return "main";
    }

    @GetMapping("/user-page/{userId}")
    public String userPage(Model model, @PathVariable(value = "userId") Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user-page";
        }
        return "redirect:/user/register";
    }

    @GetMapping("/user/delete")
    public String userDelete(@RequestParam Long userId, Model model) {
        userRepository.deleteById(userId);
        return "redirect:/";
    }

    @GetMapping("debug/drop")
    public String drop(Model model) {
        userRepository.deleteAll();
        return "redirect:/";
    }

    @GetMapping("/debug/load")
    public String debugLoad(Model model) {
        return "redirect:/";
    }

}