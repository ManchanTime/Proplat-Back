package architecture.lesserpanda.controller;

import architecture.lesserpanda.exception.UserNotFoundException;
import architecture.lesserpanda.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import static architecture.lesserpanda.dto.PostDto.*;
import static architecture.lesserpanda.dto.UserDto.*;
import static architecture.lesserpanda.global.ExceptionStatement.*;
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
            throw new IllegalStateException(LOGIN_PLEASE);
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
            throw new UserNotFoundException(LOGIN_PLEASE);
        return postService.deletePost(postId, loginUser.getLoginId());
    }
}
