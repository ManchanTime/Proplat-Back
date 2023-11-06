package architecture.lesserpanda.service;

import architecture.lesserpanda.entity.Member;
import architecture.lesserpanda.entity.Post;
import architecture.lesserpanda.entity.Reply;
import architecture.lesserpanda.exception.PostNotFoundException;
import architecture.lesserpanda.exception.ReplyNotFoundException;
import architecture.lesserpanda.exception.UserNotFoundException;
import architecture.lesserpanda.repository.PostRepository;
import architecture.lesserpanda.repository.ReplyRepository;
import architecture.lesserpanda.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static architecture.lesserpanda.dto.ReplyDto.*;
import static architecture.lesserpanda.global.ExceptionStatement.*;

/**
 * 필요 기능
 * 1. 댓글 리스트 출력
 * 2. 댓글 작성
 * 3. 댓글 삭제
 * 4. 대댓글 작성
 * 5. 대댓글 삭제
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long saveReply(Long loginId, Long postId, ReplySaveRequestDto replySaveRequestDto){
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));
        Member member = memberRepository.findById(loginId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        Reply reply = Reply.toReplyEntity(replySaveRequestDto, post, member);
        replyRepository.save(reply);

        return reply.getId();
    }

    @Transactional
    public Long saveReReply(Long loginId, Long replyId, ReplySaveRequestDto replySaveRequestDto){
        Reply parent = replyRepository.findById(replyId).orElseThrow(() -> new ReplyNotFoundException(REPLY_NOT_FOUND));
        Member member = memberRepository.findById(loginId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        Reply saveReply = Reply.toReReplyEntity(parent.getPost(), replySaveRequestDto, parent, member);
        replyRepository.save(saveReply);
        return saveReply.getId();
    }

    public Page<ReplyGetResponseDto> findAll(Long postId, Pageable pageable){
        return replyRepository.findPostReplies(postId, pageable);
    }

    @Transactional
    public String deleteReply(String loginId, Long replyId){
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new ReplyNotFoundException(REPLY_NOT_FOUND));
        if(reply.getMember().getLoginId().equals(loginId)){
            replyRepository.delete(reply);
            return "clear";
        }
        throw new IllegalStateException(NOT_OWNER);
    }

    @Transactional
    public void updateReply(Long replyId, ReplySaveRequestDto replySaveRequestDto, Long loginId){
        memberRepository.findById(loginId).orElseThrow(() -> new UserNotFoundException(NOT_OWNER));
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new ReplyNotFoundException(REPLY_NOT_FOUND));
        reply.change(replySaveRequestDto);
    }
}
