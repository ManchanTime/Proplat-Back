package architecture.lesserpanda.controller;

import architecture.lesserpanda.dto.MemberDto;
import architecture.lesserpanda.dto.TokenDto;
import architecture.lesserpanda.service.authentication.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static architecture.lesserpanda.dto.MemberDto.*;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    //회원가임
    @PostMapping("/signup")
    public ResponseEntity<LoginResponseDto> signup(@RequestBody SignUpRequestDto requestDto){
        return ok(authService.signup(requestDto));
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ok(authService.login(loginRequestDto));
    }
}
