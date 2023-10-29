package architecture.lesserpanda.dto;

import architecture.lesserpanda.entity.Post;
import architecture.lesserpanda.entity.PostStack;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        private List<TechStackPostInfoDto> postStackList = new ArrayList<>();

        @Builder
        public FindPostResponseDto(Long postId, String title, String content, boolean complete, List<TechStackPostInfoDto> postStackList) {
            this.postId = postId;
            this.title = title;
            this.content = content;
            this.complete = complete;
            this.postStackList = postStackList;
        }

        public static FindPostResponseDto toFindPostResponseDto(Post post, List<TechStackPostInfoDto> list){
            return FindPostResponseDto.builder()
                    .postId(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .complete(post.getComplete())
                    .postStackList(list)
                    .build();
        }
    }
}
