package gg.springtry.dripper_web.repo;

import gg.springtry.dripper_web.models.Anek;
import gg.springtry.dripper_web.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AnekRepository extends CrudRepository<Anek, Long> {
    Iterable<Anek> findAllByAuthor(User author);

    @Query("SELECT a FROM Anek a ORDER BY a.id DESC")
    Iterable<Anek> findAllReversed();
}
