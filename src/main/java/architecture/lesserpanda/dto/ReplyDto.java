package architecture.lesserpanda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReplyDto {
    private Long id;
    private String content;
}
