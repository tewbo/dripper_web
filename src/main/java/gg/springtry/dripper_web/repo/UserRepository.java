package gg.springtry.dripper_web.repo;

import gg.springtry.dripper_web.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
