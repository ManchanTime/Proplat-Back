package architecture.lesserpanda.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class UserStack {

    @Id @GeneratedValue
    @Column(name = "user_stack_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_stack_id")
    private TechStack techStack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public UserStack(Long id, TechStack techStack, User user) {
        this.id = id;
        this.techStack = techStack;
        this.user = user;
    }

    public static UserStack createUserStack(TechStack techStack, User user){
        return UserStack.builder().techStack(techStack).user(user).build();
    }

    public void setUser(User user){
        this.user = user;
    }
}
