package gg.springtry.dripper_web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TalkController {
    @GetMapping("/talks")
    public String talks() {
        return "talks";
    }
}
