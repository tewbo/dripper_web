package gg.springtry.dripper_web.repo;

import gg.springtry.dripper_web.models.Dialog;
import gg.springtry.dripper_web.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface DialogRepository extends CrudRepository<Dialog, Long> {
    @Query("SELECT d FROM Dialog d " +
            "WHERE :user1 MEMBER OF d.users AND :user2 MEMBER OF d.users")
    Dialog findDialogByTwoUsers(@Param("user1") User user1, @Param("user2") User user2);
}
