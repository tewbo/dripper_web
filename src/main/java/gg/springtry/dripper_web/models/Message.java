package gg.springtry.dripper_web.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private LocalDateTime creationDate;
    @ManyToOne()
    @JoinColumn(name = "dialog_id", referencedColumnName = "id")
    @JsonBackReference
    private Dialog dialog;
    @ManyToOne()
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    @JsonManagedReference
    private User sender;

    public Message(String text, Dialog dialog, User sender) {
        this.text = text;
        this.dialog = dialog;
        this.sender = sender;
        this.creationDate = LocalDateTime.now();
    }

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
