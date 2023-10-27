package architecture.lesserpanda.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class PostDto {

    @NotNull(message = "제목은 NULL 일 수 없습니다!")
    private String title;
    @NotNull(message = "내용은 NULL 일 수 없습니다!")
    private String content;
    private Boolean complete = false;
}
