package gg.springtry.dripper_web.controllers;

import gg.springtry.dripper_web.models.User;
import gg.springtry.dripper_web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        if (userService.isLogged()) {
            return "redirect:/";
        }
        model.addAttribute("userForm", new User());
        return "registration-page";
    }

    /*@PostMapping("//register")
    public String deleteThis(Model model) {
        System.out.println("kekw");
        return "redirect:/";
    }*/

    @PostMapping("/register")
    public String addUser(@ModelAttribute("userForm") User userForm, Model model) {

//        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
//            model.addAttribute("passwordError", "Пароли не совпадают");
//            return "registration";
//        }

        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration-page";
        }

        return "redirect:/";
    }
}
