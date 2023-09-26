package architecture.lesserpanda.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Getter
public class PostStack {

    @Id @GeneratedValue
    @Column(name = "post_stack_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tech_stack_id")
    private TechStack techStack;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
