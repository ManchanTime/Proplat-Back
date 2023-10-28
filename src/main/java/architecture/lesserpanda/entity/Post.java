package architecture.lesserpanda.entity;

import architecture.lesserpanda.dto.PostDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static architecture.lesserpanda.dto.PostDto.*;

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
    //private List<String> imageList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostStack> postStackList = new ArrayList<>();

    public Post(String title) {
        this.title = title;
    }

    //==연관 관계 생성 메서드==//
    public void setPostStack(PostStack postStack){
        this.postStackList.add(postStack);
        postStack.setPost(this);
    }

    @Builder
    public Post(Long id, String title, String content, Boolean complete,
                LocalDateTime date, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.complete = complete;
        this.date = date;
        //this.image = image;
        this.user = user;
        this.replyList = new ArrayList<>();
        this.postStackList = new ArrayList<>();
    }

    public static Post toEntity(SaveRequestDto saveRequestDto, User user){
        return Post
                .builder()
                .title(saveRequestDto.getTitle())
                .user(user)
                .content(saveRequestDto.getContent())
                .complete(saveRequestDto.getComplete())
                .date(LocalDateTime.now())
                .build();
    }
}
