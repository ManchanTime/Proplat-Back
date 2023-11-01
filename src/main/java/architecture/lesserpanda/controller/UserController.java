package architecture.lesserpanda.controller;

import architecture.lesserpanda.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static architecture.lesserpanda.dto.UserDto.*;
import static architecture.lesserpanda.global.ExceptionStatement.*;
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

    //로그 아웃
    @PostMapping("/user-api/logout")
    public void logout(HttpSession session){
        session.invalidate();
    }

    //마이페이지
    @GetMapping("/user-api/my-page")
    public UserInfoDto showUserInfo(@SessionAttribute(name = LOGIN_INFO) LoginResponseDto loginUser){
        if(loginUser == null){
            throw new IllegalStateException(LOGIN_PLEASE);
        }
        return userService.getUserInfo(loginUser);
    }

    //업데이트
    @PutMapping("/user-api/update")
    public void updateUser(@SessionAttribute(name = LOGIN_INFO) LoginResponseDto loginUser,
                           @RequestBody UpdateInfoDto updateUserInfo){
        if(loginUser == null){
            throw new IllegalStateException(LOGIN_PLEASE);
        }
        userService.updateUser(updateUserInfo, loginUser.getId());
    }
}
