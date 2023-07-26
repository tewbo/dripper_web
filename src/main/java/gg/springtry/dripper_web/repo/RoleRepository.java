package gg.springtry.dripper_web.repo;

import gg.springtry.dripper_web.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
