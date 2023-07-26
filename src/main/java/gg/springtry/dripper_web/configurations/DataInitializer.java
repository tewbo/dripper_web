package gg.springtry.dripper_web.configurations;

import gg.springtry.dripper_web.models.Role;
import gg.springtry.dripper_web.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Configuration
public class DataInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createRoleIfNotFound("ROLE_USER");
        createRoleIfNotFound("ROLE_ADMIN");
    }

    private void createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
    }
}