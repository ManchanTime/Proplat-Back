package architecture.lesserpanda.dto;

import architecture.lesserpanda.entity.Post;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    public static class FindPostResponseDto {
        private Long postId;
        private String title;
        private String content;
        private boolean complete;
        private String postingTime;
        private List<TechStackInfoDto> postStackList = new ArrayList<>();

        @Builder
        public FindPostResponseDto(Long postId, String title, String content, String postingTime,
                                   boolean complete, List<TechStackInfoDto> postStackList) {
            this.postId = postId;
            this.title = title;
            this.content = content;
            this.postingTime = postingTime;
            this.complete = complete;
            this.postStackList = postStackList;
        }

        public static FindPostResponseDto toFindPostResponseDto(Post post, List<TechStackInfoDto> list){
            String dateTime = post.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return FindPostResponseDto.builder()
                    .postId(post.getId())
                    .title(post.getTitle())
                    .postingTime(dateTime)
                    .content(post.getContent())
                    .complete(post.getComplete())
                    .postStackList(list)
                    .build();
        }
    }
}
