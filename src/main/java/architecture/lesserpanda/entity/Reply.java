package architecture.lesserpanda.entity;

import architecture.lesserpanda.dto.ReplyDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static architecture.lesserpanda.dto.ReplyDto.*;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@RequiredArgsConstructor
public class Reply {

    @Id @GeneratedValue
    @Column(name = "reply_id")
    private Long id;

    private String content;
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Reply parent;

    @OneToMany(mappedBy = "parent")
    private List<Reply> child = new ArrayList<>();

    //==연관 관계 생성 메서드==//
    /**
     * 대댓글 작성
     */
    public void addRereply(Reply parent){
        this.parent = parent;
        parent.getChild().add(this);
    }

    /**
     * Post에 댓글 추가
     */
    public void setPost(Post post){
        this.post = post;
        post.getReplyList().add(this);
    }

    //생성자
    @Builder
    public Reply(Long id, String content, LocalDateTime date, Post post, Reply parent, List<Reply> child) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.post = post;
        this.parent = parent;
        this.child = child;
    }

    public static Reply toEntity(ReplySaveRequestDto replySaveRequestDto, Post post){
        return Reply
                .builder()
                .content(replySaveRequestDto.getContent())
                .date(LocalDateTime.now())
                .post(post)
                .build();
    }
}
