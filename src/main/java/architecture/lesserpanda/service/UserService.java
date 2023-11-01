package architecture.lesserpanda.service;

import architecture.lesserpanda.dto.UserDto;
import architecture.lesserpanda.entity.TechStack;
import architecture.lesserpanda.entity.User;
import architecture.lesserpanda.entity.UserStack;
import architecture.lesserpanda.exception.UserNotFoundException;
import architecture.lesserpanda.global.ExceptionStatement;
import architecture.lesserpanda.repository.TechStackRepository;
import architecture.lesserpanda.repository.UserRepository;
import architecture.lesserpanda.repository.UserStackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static architecture.lesserpanda.dto.UserDto.*;
import static architecture.lesserpanda.dto.UserDto.LoginResponseDto.*;
import static architecture.lesserpanda.global.ExceptionStatement.*;

/**
    필요 기능
    1. 회원 가입
    2. 로그인
 **/
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final TechStackRepository techStackRepository;
    private final UserStackRepository userStackRepository;
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
            UserStack userStack = UserStack.createUserStack(techStack, user);
            user.getUserStackList().add(userStack);
        }
        userRepository.save(user);
        return user.getId();
    }

    public LoginResponseDto login(LoginRequestDto userLoginRequestDto){
        User user = userRepository
                .findByLoginId(userLoginRequestDto.getLoginId())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        if (!passwordEncoder.matches(userLoginRequestDto.getLoginPassword(), user.getLoginPassword())) {
            throw new IllegalArgumentException(WRONG_PASSWORD);
        }
        return toLoginResponseDto(user.getId(), user.getNickname(), user.getLoginId());
    }

    private void validateDuplicateUser(SaveRequest saveRequest) {
        if(userRepository.findByLoginId(saveRequest.getLoginId()).isPresent()){
            throw new UserNotFoundException(DUPLICATION_USER);
        }
    }

    //마이페이지
    public UserInfoDto getUserInfo(LoginResponseDto loginResponseDto){
        return userRepository.findUserInfo(loginResponseDto.getId());
    }

    //업데이트
    @Transactional
    public void updateUser(UpdateInfoDto updateUserInfo, Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        List<String> techList = updateUserInfo.getUserStackList();
        List<UserStack> beforeUserStackList = userStackRepository.findByUser(user);
        userStackRepository.deleteAll(beforeUserStackList);

        beforeUserStackList.clear();
        for (String techName : techList) {
            TechStack techStack = techStackRepository.findByName(techName);
            UserStack userStack = UserStack.createUserStack(techStack, user);
            beforeUserStackList.add(userStack);
        }
        user.update(updateUserInfo, beforeUserStackList);
    }
}
