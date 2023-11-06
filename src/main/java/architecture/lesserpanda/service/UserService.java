package architecture.lesserpanda.service;

import architecture.lesserpanda.config.SecurityUtil;
import architecture.lesserpanda.entity.Member;
import architecture.lesserpanda.entity.TechStack;
import architecture.lesserpanda.entity.UserStack;
import architecture.lesserpanda.exception.UserNotFoundException;
import architecture.lesserpanda.repository.TechStackRepository;
import architecture.lesserpanda.repository.MemberRepository;
import architecture.lesserpanda.repository.UserStackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static architecture.lesserpanda.dto.MemberDto.*;
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

    private final MemberRepository memberRepository;
    private final TechStackRepository techStackRepository;
    private final UserStackRepository userStackRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto getMyInfoBySecurity(){
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(LoginResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
    }

    //마이페이지
    public UserInfoDto getUserInfo(LoginResponseDto loginResponseDto){
        return memberRepository.findUserInfo(loginResponseDto.getId());
    }

    //업데이트
    @Transactional
    public void updateUser(UpdateInfoDto updateUserInfo, Long userId){
        Member member = memberRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        List<String> techList = updateUserInfo.getUserStackList();
        List<UserStack> beforeUserStackList = userStackRepository.findByMember(member);
        userStackRepository.deleteAll(beforeUserStackList);

        beforeUserStackList.clear();
        for (String techName : techList) {
            TechStack techStack = techStackRepository.findByName(techName);
            UserStack userStack = UserStack.createUserStack(techStack, member);
            beforeUserStackList.add(userStack);
        }
        member.update(updateUserInfo, beforeUserStackList);
    }
}
