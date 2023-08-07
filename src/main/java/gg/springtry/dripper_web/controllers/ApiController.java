package gg.springtry.dripper_web.controllers;

import gg.springtry.dripper_web.models.Dialog;
import gg.springtry.dripper_web.models.User;
import gg.springtry.dripper_web.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ApiController {
    final
    UserService userService;

    public ApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public Iterable<User> users() {
        return userService.findAllExcept(userService.getLoggedUser());
    }

    @GetMapping("/api/dialogs/{userId}")
    public Dialog getCommonDialog(@PathVariable Long userId) {
        return userService.getTwoUsersDialog(userService.getLoggedUser().getId(), userId);
    }
}
