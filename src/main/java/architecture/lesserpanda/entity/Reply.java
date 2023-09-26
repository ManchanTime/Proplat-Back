package architecture.lesserpanda.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@RequiredArgsConstructor
public class Reply {

    @Id @GeneratedValue
    @Column(name = "reply_id")
    private Long id;

    private String content;
    private LocalDateTime date;

    @ManyToOne
    @Column(name = "post_id")
    private Post post;

    @ManyToOne
    @Column(name = "reply_id")
    private Reply reply;
}
