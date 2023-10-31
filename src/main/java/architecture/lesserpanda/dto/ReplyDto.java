package architecture.lesserpanda.dto;

import architecture.lesserpanda.entity.Reply;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

public class ReplyDto {

    @Getter
    @NoArgsConstructor
    public static class ReplySaveRequestDto{
        private String content;

        @Builder
        public ReplySaveRequestDto(String content) {
            this.content = content;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ReplyGetResponseDto{
        private Long replyId;
        private String username;
        private String content;
        private List<ReReplyGetResponseDto> child_replies = new ArrayList<>();

        @Builder
        public ReplyGetResponseDto(Long replyId, String username, String content) {
            this.replyId = replyId;
            this.username = username;
            this.content = content;
        }

        public static ReplyGetResponseDto toReplyGetResponseDto(Long replyId, String username, String content){
            return ReplyGetResponseDto
                    .builder()
                    .replyId(replyId)
                    .username(username)
                    .content(content)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ReReplyGetResponseDto{
        private Long replyId;
        private String username;
        private String content;

        @Builder
        public ReReplyGetResponseDto(Long replyId, String username, String content) {
            this.replyId = replyId;
            this.username = username;
            this.content = content;
        }

        public static ReReplyGetResponseDto toReReplyGetResponseDto(Long replyId, String username, String content){
            return ReReplyGetResponseDto
                    .builder()
                    .replyId(replyId)
                    .username(username)
                    .content(content)
                    .build();
        }
    }
}
