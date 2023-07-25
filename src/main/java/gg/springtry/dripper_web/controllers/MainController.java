package gg.springtry.dripper_web.controllers;


import gg.springtry.dripper_web.models.User;
import gg.springtry.dripper_web.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        model.addAttribute("is_authorized",true);
        model.addAttribute("userId", 104L);
        return "main";
    }

    @GetMapping("/user-page/{userId}")
    public String userPage(Model model, @PathVariable(value = "userId") Long userId) {
        model.addAttribute("is_authorized",true);
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user-page";
        }
        return "redirect:/user/register";
    }

    @GetMapping("/user/register")
    public String userRegister(Model model) {
        model.addAttribute("is_authorized",true);
        return "registration-page";
    }

    /*@GetMapping("/user/login")
    public String userLogin(Model model) {
//        model.addAttribute("is_authorized",true);
        return "login";
    }*/

    @PostMapping("/user/add")
    public String userAdd(@RequestParam String username, @RequestParam(name = "password") String passwordHash, @RequestParam String email, @RequestParam String firstName, @RequestParam String lastName, Model model) {
        User user = new User(username, passwordHash, email, firstName, lastName);
        userRepository.save(user);
        return "redirect:/";
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

}