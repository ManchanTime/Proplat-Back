package architecture.lesserpanda.service;

import architecture.lesserpanda.entity.Post;
import architecture.lesserpanda.entity.Reply;
import architecture.lesserpanda.entity.User;
import architecture.lesserpanda.repository.PostRepository;
import architecture.lesserpanda.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    @Transactional
    public Long savePost(Post post, Long userId){
        User user = userRepository.findById(userId);
        post.createPost(user);
        postRepository.save(post);
        return post.getId();
    }

    public Post findPost(Long id){
        return postRepository.findById(id);
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    @Transactional
    public void deletePost(Long postId){
        Post post = postRepository.findById(postId);
        postRepository.delete(post);
    }
}
