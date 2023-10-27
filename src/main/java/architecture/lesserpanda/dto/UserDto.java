package architecture.lesserpanda.dto;

import architecture.lesserpanda.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserDto {

    @NotBlank
    @Getter
    @NoArgsConstructor
    public static class SaveRequest{

        private String name;
        @Email
        private String loginId;
        private String loginPassword;
        private String nickname;
        private String phoneNumber;
        private String introduce;
        private List<String> techList = new ArrayList<>();

        public SaveRequest(String name, String loginId, String loginPassword, String nickname,
                           String phoneNumber, String introduce, List<String> techList) {
            this.name = name;
            this.loginId = loginId;
            this.loginPassword = loginPassword;
            this.nickname = nickname;
            this.phoneNumber = phoneNumber;
            this.introduce = introduce;
            this.techList = techList;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class LoginRequestDto {
        private String loginId;
        private String loginPassword;

        @Builder
        public LoginRequestDto(String loginId, String loginPassword) {
            this.loginId = loginId;
            this.loginPassword = loginPassword;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class LoginResponseDto {
        private Long id;
        private String nickname;
        private String loginId;

        @Builder
        public LoginResponseDto(Long id, String nickname, String loginId) {
            this.id = id;
            this.nickname = nickname;
            this.loginId = loginId;
        }

        public static LoginResponseDto toLoginResponseDto(Long id, String nickname, String loginId){
            return LoginResponseDto
                    .builder()
                    .id(id)
                    .nickname(nickname)
                    .loginId(loginId)
                    .build();
        }
    }
}
