package gg.springtry.dripper_web.controllers;

import gg.springtry.dripper_web.models.User;
import gg.springtry.dripper_web.repo.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("user")
    public User LoadUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth == null ? null : auth.getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        }
        return null;
    }
}
