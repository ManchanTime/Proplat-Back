package architecture.lesserpanda.service;

import architecture.lesserpanda.entity.Post;
import architecture.lesserpanda.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class PostServiceTest {

    @Autowired PostService postService;
    @Test
    public void createReply(){
        Post post = new Post("test");
        Long id = postService.savePost(post);

        Post findPost = postService.findPost(id);

        assertThat(findPost).isEqualTo(post);
    }

    @Test
    public void findAll(){
        Post post1 = new Post("post1");
        Post post2 = new Post("post2");

        postService.savePost(post1);
        postService.savePost(post2);

        List<Post> all = postService.findAll();
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    public void deleteReply(){
        Post post1 = new Post("post1");
        Post post2 = new Post("post2");

        Long id1 = postService.savePost(post1);
        Long id2 = postService.savePost(post2);

        postService.deletePost(id1);

        List<Post> all = postService.findAll();
        assertThat(all.size()).isEqualTo(1);
    }
}