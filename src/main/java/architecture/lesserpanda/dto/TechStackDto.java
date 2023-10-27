package architecture.lesserpanda.dto;

import architecture.lesserpanda.entity.TechStack;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class TechStackDto {

    @Getter
    @Builder
    public static class TechStackInfoDto{
        private String name;
        private List<ClubDto> clubs;
    }

    @Getter
    @NoArgsConstructor
    public static class RequestSave{
        private String name;

        public RequestSave(String name) {
            this.name = name;
        }

        @Builder
        public TechStack toEntity(){
            return TechStack.builder()
                    .name(this.name).build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ChooseTech{
        private Long techId;

        @Builder
        public ChooseTech(Long techId) {
            this.techId = techId;
        }
    }
}
