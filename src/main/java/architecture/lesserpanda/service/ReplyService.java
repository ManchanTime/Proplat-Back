package architecture.lesserpanda.service;

import architecture.lesserpanda.entity.Post;
import architecture.lesserpanda.entity.Reply;
import architecture.lesserpanda.repository.PostRepository;
import architecture.lesserpanda.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

//    @Transactional
//    public Long saveReply(Reply reply, Long postId){
//        Post post = postRepository.findById(postId);
//        reply.setPost(post);
//        replyRepository.save(reply);
//        return reply.getId();
//    }
//
//    public Reply findReply(Long id){
//        return replyRepository.findById(id);
//    }
//
//    public List<Reply> findAll(){
//        return replyRepository.findAll();
//    }
//
//    public List<Reply> findByPostId(Long postId){
//        return replyRepository.findByPostId(postId);
//    }
//
//    @Transactional
//    public void deleteReply(Long replyId){
//        Reply reply = replyRepository.findById(replyId);
//        replyRepository.delete(reply);
//    }
}
