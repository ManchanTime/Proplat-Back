package architecture.lesserpanda.dto;

import architecture.lesserpanda.dto.TechStackDto.TechStackInfoDto;
import architecture.lesserpanda.entity.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Getter
    @Builder
    public static class UserInfoDto{
        private String name;
        private String loginId;
        private String nickname;
        private String phoneNumber;
        private String introduce;
        private List<TechStackInfoDto> userStackList = new ArrayList<>();

        public static UserInfoDto toUserInfoDto(User user, List<TechStackInfoDto> techStackInfoDtoList) {
            return UserInfoDto.builder()
                    .name(user.getName())
                    .loginId(user.getLoginId())
                    .nickname(user.getNickname())
                    .phoneNumber(user.getPhoneNumber())
                    .introduce(user.getIntroduce())
                    .userStackList(techStackInfoDtoList)
                    .build();
        }
    }
}
