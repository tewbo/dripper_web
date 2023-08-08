package gg.springtry.dripper_web.services;

import gg.springtry.dripper_web.models.Dialog;
import gg.springtry.dripper_web.repo.DialogRepository;
import gg.springtry.dripper_web.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    final
    MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository, DialogRepository dialogRepository) {
        this.messageRepository = messageRepository;
        this.dialogRepository = dialogRepository;
    }

    final
    DialogRepository dialogRepository;
}
