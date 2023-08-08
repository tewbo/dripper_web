package gg.springtry.dripper_web.services;

import gg.springtry.dripper_web.models.Dialog;
import gg.springtry.dripper_web.models.Message;
import gg.springtry.dripper_web.models.Role;
import gg.springtry.dripper_web.models.User;
import gg.springtry.dripper_web.repo.DialogRepository;
import gg.springtry.dripper_web.repo.MessageRepository;
import gg.springtry.dripper_web.repo.RoleRepository;
import gg.springtry.dripper_web.repo.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    final
    UserRepository userRepository;
    final
    RoleRepository roleRepository;
    final
    DialogRepository dialogRepository;
    final
    MessageRepository messageRepository;

    final
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, DialogRepository dialogRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.dialogRepository = dialogRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public Iterable<User> allUsers() {
        return userRepository.findAll();
    }

    public Iterable<User> allUsersSortedByPostsCount() {
        return userRepository.findAllSortedByPostsCount();
    }

    public void saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            throw new ConstraintViolationException("Пользователь с таким именем уже существует", null);
        }
        if (user.getPassword().length() > 16 || user.getPassword().length() < 4) {
            throw new ConstraintViolationException("Пароль должен быть от 4 до 16 символов", null);
        }
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role defaultRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(Collections.singleton(defaultRole));
        userRepository.save(user);
    }

    public boolean deleteUser(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        if (userFromDb.isPresent()) {
            User user = userFromDb.get();
            user.getRoles().clear();
            user.getAneks().clear();
            dialogRepository.deleteAll(user.getDialogs());
            userRepository.delete(userFromDb.get());
            return true;
        }
        return false;
    }

    public User getLoggedUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication == null ? null : (User) authentication.getPrincipal();
    }

    public boolean isLogged() {
        return getLoggedUser() != null;
    }

    public Long getAneksCount(Long userId) {
        return em.createQuery("select count(a) from Anek a where a.author.id = :userId", Long.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    public Iterable<User> findAllExcept(User user) {
        return userRepository.findAllExcept(user.getId());
    }

    public Dialog getTwoUsersDialog(Long userId1, Long userId2) {
        Optional<User> user1 = userRepository.findById(userId1);
        Optional<User> user2 = userRepository.findById(userId2);
        if (user1.isPresent() && user2.isPresent()) {
            Dialog commonDialog = dialogRepository.findDialogByTwoUsers(user1.get(), user2.get());
            if (commonDialog != null) {
                return commonDialog;
            }
            Dialog dialog = new Dialog();
            dialog.setUsers(new HashSet<>(Set.of(user1.get(), user2.get())));
            dialogRepository.save(dialog);
            return dialog;
        }
        return null;
    }

    public void sendMessage(Long dialogId, String message) {
        Optional<Dialog> dialog = dialogRepository.findById(dialogId);
        if (dialog.isPresent()) {
            Message newMessage = new Message(message, dialog.get(), getLoggedUser());
            messageRepository.save(newMessage);
        }
    }


}
