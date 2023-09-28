package architecture.lesserpanda.service;

import architecture.lesserpanda.entity.Post;
import architecture.lesserpanda.entity.Reply;
import architecture.lesserpanda.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long savePost(Post post){
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
