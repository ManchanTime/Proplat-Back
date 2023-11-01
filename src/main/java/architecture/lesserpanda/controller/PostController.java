package architecture.lesserpanda.controller;

import architecture.lesserpanda.dto.ReplyDto;
import architecture.lesserpanda.exception.UserNotFoundException;
import architecture.lesserpanda.service.PostService;
import architecture.lesserpanda.service.ReplyService;
import architecture.lesserpanda.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import static architecture.lesserpanda.dto.PostDto.*;
import static architecture.lesserpanda.dto.ReplyDto.*;
import static architecture.lesserpanda.dto.UserDto.*;
import static architecture.lesserpanda.global.SessionConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/save-api")
    public SaveRequestDto savePost(@SessionAttribute(name = LOGIN_INFO) LoginResponseDto loginUser,
                                   @RequestBody SaveRequestDto saveRequestDto){
        if(loginUser == null){
            throw new IllegalStateException("로그인을 해주세요.");
        }
        postService.save(saveRequestDto, loginUser);
        return saveRequestDto;
    }

    @GetMapping("/list-api")
    public Page<FindPostResponseDto> postList(@RequestParam(required = false) String keyword, Pageable pageable){
        return postService.findAllPost(keyword, pageable);
    }

    @GetMapping("/postId={postId}")
    public FindPostResponseDto choosePost(@PathVariable Long postId){
        return postService.findPostById(postId);
    }

    @DeleteMapping("/postId={postId}")
    public String deletePost(@SessionAttribute(name = LOGIN_INFO) LoginResponseDto loginUser,
                                          @PathVariable Long postId){
        if(loginUser == null)
            throw new UserNotFoundException("로그인을 해주세요.");
        return postService.deletePost(postId, loginUser.getLoginId());
    }
}
