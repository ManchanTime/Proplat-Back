package architecture.lesserpanda.controller;

import architecture.lesserpanda.dto.UserDto;
import architecture.lesserpanda.entity.Post;
import architecture.lesserpanda.entity.Reply;
import architecture.lesserpanda.entity.User;
import architecture.lesserpanda.dto.PostDto;
import architecture.lesserpanda.dto.ReplyDto;
import architecture.lesserpanda.global.SessionConstants;
import architecture.lesserpanda.service.PostService;
import architecture.lesserpanda.service.ReplyService;
import architecture.lesserpanda.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.List;

import static architecture.lesserpanda.dto.PostDto.*;
import static architecture.lesserpanda.dto.UserDto.*;
import static architecture.lesserpanda.global.SessionConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final ReplyService replyService;

    @PostMapping("/save-api")
    public SaveRequestDto savePost(@SessionAttribute(name = LOGIN_INFO) LoginResponseDto loginUser,
                                   @RequestBody SaveRequestDto saveRequestDto){
        if(loginUser == null){
            throw new IllegalStateException("로그인 안돼있음");
        }
        postService.save(saveRequestDto, loginUser);
        return saveRequestDto;
    }

    @GetMapping("/list-api")
    public Page<PostListResponseDto> postList(@RequestParam(required = false) String keyword, Pageable pageable){
        return postService.findAllPost(keyword, pageable);
    }
}
