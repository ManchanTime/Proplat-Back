package architecture.lesserpanda.service;

import architecture.lesserpanda.dto.UserDto;
import architecture.lesserpanda.entity.TechStack;
import architecture.lesserpanda.entity.User;
import architecture.lesserpanda.entity.UserStack;
import architecture.lesserpanda.repository.TechStackRepository;
import architecture.lesserpanda.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static architecture.lesserpanda.dto.UserDto.*;

/*
    필요 기능
    1. 회원 가입
    2. 로그인
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final TechStackRepository techStackRepository;
    private final PasswordEncoder passwordEncoder;

    //회원 가입
    @Transactional
    public Long join(SaveRequest saveRequest){
        validateDuplicateUser(saveRequest);
        String encodePassword = passwordEncoder.encode(saveRequest.getLoginPassword());
        User user = User.toEntity(saveRequest, encodePassword);

        List<String> techList = saveRequest.getTechList();
        for (String techName : techList) {
            TechStack techStack = techStackRepository.findByName(techName);
            UserStack userStack = UserStack.createUserStack(techStack);
            user.addUserStack(userStack);
        }
        userRepository.save(user);
        return user.getId();
    }

    public LoginResponseDto login(LoginRequestDto userLoginRequestDto){
        User findUser = userRepository.findByLoginId(userLoginRequestDto.getLoginId()).orElseThrow(() -> new IllegalArgumentException());

        boolean check = passwordEncoder.matches(userLoginRequestDto.getLoginPassword(), findUser.getLoginPassword());
        if(check){
            return LoginResponseDto.toLoginResponseDto(findUser.getId(), findUser.getNickname(), findUser.getLoginId());
        }
        else throw new IllegalArgumentException("비밀번호가 틀립니다.");
    }

    private void validateDuplicateUser(SaveRequest saveRequest) {
        if(userRepository.findByLoginId(saveRequest.getLoginId()).isPresent()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //마이페이지
    public UserInfoDto getUserInfo(LoginResponseDto loginResponseDto){
        User user = userRepository.findById(loginResponseDto.getId());

        UserInfoDto userInfo = UserInfoDto.toUserInfoDto(user);

        return userInfo;

    }
}
