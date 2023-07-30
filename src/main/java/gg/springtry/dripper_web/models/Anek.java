package gg.springtry.dripper_web.models;

import jakarta.persistence.*;

@Entity
public class Anek {
    public static final int MAX_CONTENT_LENGTH = 4000;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = MAX_CONTENT_LENGTH)
    private String content;
    private Long likes = 0L;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User author;

    public Anek() {
    }

    public Anek(String content, User author) {
        this.content = content;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String text) {
        this.content = text;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
