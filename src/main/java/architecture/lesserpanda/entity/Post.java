package architecture.lesserpanda.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String content;
    private Boolean complete;
    private LocalDateTime date;
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Reply> replyList = new ArrayList<>();

    public Post(String title) {
        this.title = title;
    }

    public Post(String title, String content, Boolean complete, LocalDateTime date, User user) {
        this.title = title;
        this.content = content;
        this.complete = complete;
        this.date = date;
        this.user = user;
    }

    public Post(String title, String content, Boolean complete, LocalDateTime date, String image, User user) {
        this.title = title;
        this.content = content;
        this.complete = complete;
        this.date = date;
        this.image = image;
        this.user = user;
    }
}
