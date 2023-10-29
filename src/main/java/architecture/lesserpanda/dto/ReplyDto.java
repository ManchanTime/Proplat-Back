package architecture.lesserpanda.dto;

import lombok.*;

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
}
