package architecture.lesserpanda.controller;

import architecture.lesserpanda.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import static architecture.lesserpanda.dto.ReplyDto.*;
import static architecture.lesserpanda.dto.UserDto.*;
import static architecture.lesserpanda.global.SessionConstants.LOGIN_INFO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/postId={postId}/reply-save-api")
    public ReplySaveRequestDto replySave(@SessionAttribute(name = LOGIN_INFO) LoginResponseDto loginUser,
                                        @PathVariable Long postId,
                                         @RequestBody ReplySaveRequestDto replySaveRequestDto){
        replyService.saveReply(loginUser.getId(), postId, replySaveRequestDto);
        return replySaveRequestDto;
    }

    @GetMapping("/postId={postId}/reply-list-api")
    public Page<ReplyGetResponseDto> replyList(@PathVariable Long postId, Pageable pageable){
        return replyService.findAll(postId, pageable);
    }

    @PostMapping("/postId={postId}/rereply-save-api")
    public ReplySaveRequestDto reReplySave(@SessionAttribute(name = LOGIN_INFO) LoginResponseDto loginUser,
                                         @RequestParam("replyId") Long replyId,
                                         @RequestBody ReplySaveRequestDto replySaveRequestDto){
        replyService.saveReReply(loginUser.getId(), replyId, replySaveRequestDto);
        return replySaveRequestDto;
    }

    @DeleteMapping("/replyId={replyId}/reply-delete-api")
    public String deleteReply(@SessionAttribute(name = LOGIN_INFO) LoginResponseDto loginUser, @PathVariable Long replyId){
        return replyService.deleteReply(loginUser.getLoginId(), replyId);
    }
}
