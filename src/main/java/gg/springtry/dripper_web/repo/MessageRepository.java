package gg.springtry.dripper_web.repo;

import gg.springtry.dripper_web.models.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
