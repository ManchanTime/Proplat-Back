package architecture.lesserpanda.dto;

import architecture.lesserpanda.entity.Post;
import architecture.lesserpanda.entity.PostStack;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static architecture.lesserpanda.dto.PostStackDto.*;
import static architecture.lesserpanda.dto.TechStackDto.*;

@Data
@AllArgsConstructor
public class PostDto {

    @Getter
    @NoArgsConstructor
    public static class SaveRequestDto{
        @NotNull(message = "제목은 NULL 일 수 없습니다!")
        private String title;
        @NotNull(message = "내용은 NULL 일 수 없습니다!")
        private String content;
        private Boolean complete = false;
        private List<String> stackList = new ArrayList<>();
        //private List<String> imageList = new ArrayList<>();

        @Builder
        public SaveRequestDto(String title, String content,
                              Boolean complete, List<String> stackList) {
            this.title = title;
            this.content = content;
            this.complete = complete;
            this.stackList = stackList;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class PostListResponseDto{
        private Long postId;
        private String title;
        private String content;
        private boolean complete;
        private String stackList;

        @Builder
        public PostListResponseDto(Long postId, String title, String content, boolean complete, String stackList) {
            this.postId = postId;
            this.title = title;
            this.content = content;
            this.complete = complete;
            this.stackList = stackList;
        }

        public static PostListResponseDto toEntity(Post post){
            return PostListResponseDto
                    .builder()
                    .postId(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .complete(post.getComplete())
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class LoadRequestDto{
        private Long postId;

        @Builder
        public LoadRequestDto(Long postId) {
            this.postId = postId;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class LoadResponseDto{
        private Long postId;
        private String title;
        private String content;

    }
}
