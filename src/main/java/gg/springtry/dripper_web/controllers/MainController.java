package gg.springtry.dripper_web.controllers;


import gg.springtry.dripper_web.models.Anek;
import gg.springtry.dripper_web.models.User;
import gg.springtry.dripper_web.repo.UserRepository;
import gg.springtry.dripper_web.services.AnekService;
import gg.springtry.dripper_web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    private final UserService userService;
    private final AnekService anekService;

    public MainController(UserService userService, AnekService anekService) {
        this.userService = userService;
        this.anekService = anekService;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("aneks", anekService.allAneks());
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

    @GetMapping("/top-users")
    public String topUsers(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "top-users";
    }

    @PostMapping("/add-anek")
    public String addAnek(@RequestParam String content, Model model) {
        if (anekService.addAnek(content, userService.getLoggedUser())) {
            return "redirect:/?anekAddSuccess";
        }
        return "redirect:/?anekAddError&max_length=" + Anek.MAX_CONTENT_LENGTH;
    }

}