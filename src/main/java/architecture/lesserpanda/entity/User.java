package architecture.lesserpanda.entity;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String loginId;
    private String loginPassword;
    private String nickname;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserStack> userStackList = new ArrayList<>();
}
