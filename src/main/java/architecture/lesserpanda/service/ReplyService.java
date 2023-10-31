package architecture.lesserpanda.service;

import architecture.lesserpanda.entity.Post;
import architecture.lesserpanda.entity.Reply;
import architecture.lesserpanda.entity.User;
import architecture.lesserpanda.repository.PostRepository;
import architecture.lesserpanda.repository.ReplyRepository;
import architecture.lesserpanda.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static architecture.lesserpanda.dto.ReplyDto.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long saveReply(Long loginId, Long postId, ReplySaveRequestDto replySaveRequestDto){
        Post post = postRepository.findById(postId).orElseThrow();
        User user = userRepository.findById(loginId).orElseThrow();
        Reply reply = Reply.toReplyEntity(replySaveRequestDto, post, user);
        replyRepository.save(reply);

        return reply.getId();
    }

    @Transactional
    public Long saveReReply(Long loginId, Long replyId, ReplySaveRequestDto replySaveRequestDto){
        Reply parent = replyRepository.findById(replyId).orElseThrow();
        User user = userRepository.findById(loginId).orElseThrow();
        Reply saveReply = Reply.toReReplyEntity(parent.getPost(), replySaveRequestDto, parent, user);
        replyRepository.save(saveReply);
        return saveReply.getId();
    }

    public Page<ReplyGetResponseDto> findAll(Long postId, Pageable pageable){
        return replyRepository.findPostReplies(postId, pageable);
    }

    @Transactional
    public String deleteReply(String loginId, Long replyId){
        Reply reply = replyRepository.findById(replyId).orElseThrow();
        if(reply.getUser().getLoginId().equals(loginId)){
            replyRepository.delete(reply);
            return "clear";
        }
        throw new IllegalStateException("로그인 안돼있음");
    }
}
