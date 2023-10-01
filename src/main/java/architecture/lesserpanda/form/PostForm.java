package architecture.lesserpanda.form;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class PostForm {

    @NotNull(message = "제목은 NULL 일 수 없습니다!")
    private String title;
    @NotNull(message = "내용은 NULL 일 수 없습니다!")
    private String content;
    private Boolean complete = false;
}
