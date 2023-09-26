package architecture.lesserpanda.entity;

import jakarta.persistence.*;
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

//    @OneToMany(mappedBy = "techStack", fetch = FetchType.LAZY)
//    private List<ClubStack> clubStackList;
//
//    @OneToMany(mappedBy = "techStack", fetch = FetchType.LAZY)
//    private List<UserStack> userStackList;
//
//    @OneToMany(mappedBy = "techStack", fetch = FetchType.LAZY)
//    private List<PostStack> postStackList;
}
