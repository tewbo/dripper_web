package gg.springtry.dripper_web.services;

import gg.springtry.dripper_web.models.Anek;
import gg.springtry.dripper_web.models.User;
import gg.springtry.dripper_web.repo.AnekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnekService {
    private final AnekRepository anekRepository;

    public AnekService(AnekRepository anekRepository) {
        this.anekRepository = anekRepository;
    }

    public boolean addAnek(String content, User author) {
        if (content == null || content.isEmpty() || content.length() > Anek.MAX_CONTENT_LENGTH) {
            return false;
        }
        Anek anek = new Anek(content, author);
        anekRepository.save(anek);
        return true;
    }

    public Iterable<Anek> allAneks() {
        return anekRepository.findAllReversed();
    }
}
