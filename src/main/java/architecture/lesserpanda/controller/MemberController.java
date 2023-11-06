package architecture.lesserpanda.controller;

import architecture.lesserpanda.config.SecurityUtil;
import architecture.lesserpanda.entity.Member;
import architecture.lesserpanda.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static architecture.lesserpanda.dto.MemberDto.*;
import static architecture.lesserpanda.global.ExceptionStatement.*;
import static architecture.lesserpanda.config.SessionConstants.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final UserService userService;
    /**
     * DB에 유저 정보가 있는지 확인
     */

    //마이페이지
    @GetMapping("/my-page")
    public ResponseEntity<UserInfoDto> showUserInfo(){
        return ResponseEntity.ok(userService.getUserInfo());
    }

    //업데이트
    @PutMapping("/update")
    public ResponseEntity<UserInfoDto> updateUser(@RequestBody UpdateInfoDto updateUserInfo){
        return ResponseEntity.ok(userService.updateUser(updateUserInfo));
    }
}
