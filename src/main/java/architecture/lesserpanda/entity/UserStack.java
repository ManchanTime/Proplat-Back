package architecture.lesserpanda.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class UserStack {

    @Id @GeneratedValue
    @Column(name = "user_stack_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tech_stack_id")
    private TechStack techStack;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
