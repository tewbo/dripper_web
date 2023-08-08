package gg.springtry.dripper_web.controllers;


import gg.springtry.dripper_web.models.Anek;
import gg.springtry.dripper_web.services.AnekService;
import gg.springtry.dripper_web.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        return "current-user-page";
    }

    @GetMapping("/top-users")
    public String topUsers(Model model) {
        model.addAttribute("users", userService.allUsersSortedByPostsCount());
        return "top-users";
    }

    @PostMapping("/add-anek")
    public String addAnek(@RequestParam String content, Model model) {
        if (anekService.addAnek(content, userService.getLoggedUser())) {
            return "redirect:/?anekAddSuccess";
        }
        return "redirect:/?anekAddError&max_length=" + Anek.MAX_CONTENT_LENGTH;
    }

    @GetMapping("/nothing")
    public String nothing(Model model) {
        return "nothing";
    }

    @GetMapping("/debug/dialog")
    public String debugDialog(Model model) {
//        Object lol = dialogRepository.testCommand(null, null);
//        model.addAttribute("dialog", dialogRepository.testCommand(null, null));
        return "nothing";
    }

}