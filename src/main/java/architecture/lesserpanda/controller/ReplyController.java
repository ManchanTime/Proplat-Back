package architecture.lesserpanda.controller;

import architecture.lesserpanda.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import static architecture.lesserpanda.dto.ReplyDto.*;
import static architecture.lesserpanda.dto.MemberDto.*;
import static architecture.lesserpanda.global.ExceptionStatement.*;
import static architecture.lesserpanda.config.SessionConstants.LOGIN_INFO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class ReplyController {

    private final ReplyService replyService;

    //댓글 작성
    @PostMapping("/postId={postId}/reply-save-api")
    public ReplySaveRequestDto replySave(@SessionAttribute(name = LOGIN_INFO) LoginResponseDto loginUser,
                                        @PathVariable Long postId,
                                         @RequestBody ReplySaveRequestDto replySaveRequestDto){
        replyService.saveReply(loginUser.getId(), postId, replySaveRequestDto);
        return replySaveRequestDto;
    }

    //댓글 리스트
    @GetMapping("/postId={postId}/reply-list-api")
    public Page<ReplyGetResponseDto> replyList(@PathVariable Long postId, Pageable pageable){
        return replyService.findAll(postId, pageable);
    }

    //대댓글 작성
    @PostMapping("/postId={postId}/rereply-save-api")
    public ReplySaveRequestDto reReplySave(@SessionAttribute(name = LOGIN_INFO) LoginResponseDto loginUser,
                                         @RequestParam("replyId") Long replyId,
                                         @RequestBody ReplySaveRequestDto replySaveRequestDto){
        if(loginUser == null){
            throw new IllegalStateException(LOGIN_PLEASE);
        }
        replyService.saveReReply(loginUser.getId(), replyId, replySaveRequestDto);
        return replySaveRequestDto;
    }

    //삭제
    @DeleteMapping("/replyId={replyId}/reply-delete-api")
    public String deleteReply(@SessionAttribute(name = LOGIN_INFO) LoginResponseDto loginUser, @PathVariable Long replyId){
        if(loginUser == null){
            throw new IllegalStateException(LOGIN_PLEASE);
        }
        return replyService.deleteReply(loginUser.getLoginId(), replyId);
    }

    //업데이트
    @PutMapping("/replyId={replyId}/reply-update-api")
    public void updateReply(@SessionAttribute(name = LOGIN_INFO) LoginResponseDto loginUser,
                            @PathVariable Long replyId,
                            @RequestBody ReplySaveRequestDto replySaveRequestDto){
        if(loginUser == null){
            throw new IllegalStateException(LOGIN_PLEASE);
        }
        replyService.updateReply(replyId, replySaveRequestDto, loginUser.getId());
    }
}
