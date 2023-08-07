package gg.springtry.dripper_web.models;


import java.util.List;
import java.util.Set;
import gg.springtry.dripper_web.models.User;
import jakarta.persistence.*;

@Entity
public class Dialog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "dialog")
    private List<Message> messages;
//    @Transient
    @ManyToMany(mappedBy = "dialogs", fetch = FetchType.EAGER)
    private Set<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
