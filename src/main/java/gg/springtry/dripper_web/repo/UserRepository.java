package gg.springtry.dripper_web.repo;

import gg.springtry.dripper_web.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    @Query("SELECT u FROM User u ORDER BY SIZE(u.aneks) DESC")
    Iterable<User> findAllSortedByPostsCount();
}
