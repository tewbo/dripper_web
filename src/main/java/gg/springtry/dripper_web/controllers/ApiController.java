package gg.springtry.dripper_web.controllers;

import gg.springtry.dripper_web.models.Dialog;
import gg.springtry.dripper_web.models.MessageText;
import gg.springtry.dripper_web.models.User;
import gg.springtry.dripper_web.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Dialog> getCommonDialog(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getTwoUsersDialog(userService.getLoggedUser().getId(), userId));
    }

    @RequestMapping(value = "/api/dialogs/{dialogId}", method = RequestMethod.POST)
    public ResponseEntity<String> sendMessageToDialog(
            @PathVariable(name = "dialogId") Long dialogId,
            @RequestBody MessageText message) {
        try {
            userService.sendMessage(dialogId, message.getText());
            return ResponseEntity.ok("Сообщение успешно отправлено.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Произошла ошибка при отправке сообщения: " + e.getMessage());
        }
    }

}
