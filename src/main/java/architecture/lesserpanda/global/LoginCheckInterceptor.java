package architecture.lesserpanda.global;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

import static architecture.lesserpanda.dto.UserDto.*;


public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1. 세션에서 회원 정보 조회
        HttpSession session = request.getSession();
        LoginResponseDto loginInfo = (LoginResponseDto) session.getAttribute("loginInfo");

        // 2. 회원 정보 체크
        if (loginInfo == null) {
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
