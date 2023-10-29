package architecture.lesserpanda.dto;

import architecture.lesserpanda.entity.Post;
import architecture.lesserpanda.entity.TechStack;
import architecture.lesserpanda.entity.TechType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static architecture.lesserpanda.dto.PostDto.*;

public class TechStackDto {

    @Getter
    @Builder
    public static class TechStackInfoDto{
        private String name;
        private List<ClubDto> clubs;
    }

    @Getter
    @NoArgsConstructor
    public static class TechStackPostInfoDto{
        private String name;
        private TechType type;

        @Builder
        public TechStackPostInfoDto(String name, TechType type) {
            this.name = name;
            this.type = type;
        }

        public static TechStackPostInfoDto toTechStackPostInfoDto(TechStack techStack){
            return TechStackPostInfoDto.builder()
                    .name(techStack.getName())
                    .type(techStack.getType())
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class RequestSave{
        private String name;
        private TechType type;
        public RequestSave(String name, TechType type) {
            this.name = name;
            this.type = type;
        }

        @Builder
        public TechStack toEntity(){
            return TechStack.builder()
                    .name(this.name).type(this.type).build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ResponseDto{
        private String name;
        private TechType type;

        @Builder
        public ResponseDto(String name, TechType type) {
            this.name = name;
            this.type = type;
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
