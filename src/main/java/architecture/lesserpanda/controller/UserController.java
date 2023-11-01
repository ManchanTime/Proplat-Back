package architecture.lesserpanda.controller;

import architecture.lesserpanda.dto.PostDto;
import architecture.lesserpanda.dto.TechStackDto;
import architecture.lesserpanda.dto.UserDto;
import architecture.lesserpanda.entity.TechStack;
import architecture.lesserpanda.global.SessionConstants;
import architecture.lesserpanda.service.TechStackService;
import architecture.lesserpanda.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static architecture.lesserpanda.dto.UserDto.*;
import static architecture.lesserpanda.global.SessionConstants.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    /**
     * DB에 유저 정보가 있는지 확인
     */
    //회원가입
    @PostMapping("/user-api/signup")
    public SaveRequest joinUser(@RequestBody SaveRequest userSaveRequestDto){
        userService.join(userSaveRequestDto);
        return userSaveRequestDto;
    }

    //로그인
    @PostMapping("/user-api/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequest, HttpServletRequest request){
        LoginResponseDto loginInfo = userService.login(loginRequest);
        if(loginInfo != null){
            request.getSession().invalidate();
            HttpSession session = request.getSession();
            session.setAttribute(LOGIN_INFO, loginInfo);
            session.setMaxInactiveInterval(1800);
        }
        return loginInfo;
    }

    //마이페이지
    @GetMapping("/user-api/my-page")
    public UserInfoDto showUserInfo(@SessionAttribute(name = LOGIN_INFO) LoginResponseDto loginUser){
        if(loginUser == null){
            throw new IllegalStateException("로그인 안돼있음");
        }
        return userService.getUserInfo(loginUser);
    }
}
