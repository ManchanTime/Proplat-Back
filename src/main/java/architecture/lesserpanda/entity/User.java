package architecture.lesserpanda.entity;

import architecture.lesserpanda.dto.PostDto;
import architecture.lesserpanda.dto.UserDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static architecture.lesserpanda.dto.UserDto.*;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserStack> userStackList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> userPostList = new ArrayList<>();

    //==연관 관계 매핑 함수==//

    /**
     * 유저 생성 시 userStack 넣어서 생성
     */
    public void addUserStack(UserStack userStack){
        this.userStackList.add(userStack);
        userStack.setUser(this);
    }

    @Builder
    public User(Long id, String name, String loginId, String loginPassword,
                String nickname, String phoneNumber, String introduce) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.introduce = introduce;
        this.userStackList = new ArrayList<>();
        this.userPostList = new ArrayList<>();
    }

    public static User toEntity(SaveRequest saveRequest, String encodePassword){
        return  User.builder()
                .name(saveRequest.getName())
                .loginId(saveRequest.getLoginId())
                .loginPassword(encodePassword)
                .nickname(saveRequest.getNickname())
                .phoneNumber(saveRequest.getPhoneNumber())
                .introduce(saveRequest.getIntroduce())
                .build();
    }
}
