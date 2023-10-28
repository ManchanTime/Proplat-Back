package architecture.lesserpanda.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
public class TechStack {

    @Id @GeneratedValue
    @Column(name = "tech_stack_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "techStack")
    private List<ClubStack> clubStackList;

    @OneToMany(mappedBy = "techStack")
    private List<UserStack> userStackList;

    @OneToMany(mappedBy = "techStack")
    private List<PostStack> postStackList;

    //DTO로 만들었는데 이 부분 중복 사용 상관없는지 확인 필요할듯
    @Builder
    public TechStack(String name) {
        this.name = name;
    }
}
