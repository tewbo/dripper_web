package gg.springtry.dripper_web.models;

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
    private Dialog dialog;

    public Message(String text, Dialog dialog) {
        this.text = text;
        this.dialog = dialog;
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
