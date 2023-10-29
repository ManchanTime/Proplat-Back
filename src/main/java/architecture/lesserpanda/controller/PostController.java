package architecture.lesserpanda.controller;

import architecture.lesserpanda.dto.ReplyDto;
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
            throw new IllegalStateException("로그인이 안돼있음");
        return postService.deletePost(postId, loginUser.getLoginId());
    }

    @PostMapping("/postId={postId}/reply-api")
    public ReplySaveRequestDto replySave(@PathVariable Long postId, @RequestBody ReplySaveRequestDto replySaveRequestDto){
        replyService.saveReply(postId, replySaveRequestDto);
        return replySaveRequestDto;
    }

}
