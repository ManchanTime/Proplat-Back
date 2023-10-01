package architecture.lesserpanda.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name = "Member")
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String loginId;
    private String loginPassword;
    private String nickname;
    private String phoneNumber;
    private String introduce;

    @OneToMany(mappedBy = "user")
    private List<UserStack> userStackList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Post> userPostList = new ArrayList<>();

    //==연관 관계 매핑 함수==//

    /**
     * 유저 생성 시 userStack 넣어서 생성
     */
    public void addUserStack(UserStack userStack){
        userStackList.add(userStack);
        userStack.setUser(this);
    }

    //생성자
    public User(String name) {
        this.name = name;
    }

    public User(String name, String loginId, String loginPassword) {
        this.name = name;
        this.loginId = loginId;
        this.loginPassword = loginPassword;
    }

    /**
     * setter 대신 사용 -> Json 사용시 필요없?!
     */
    public void setPassword(String password){
        this.loginPassword = password;
    }
}
