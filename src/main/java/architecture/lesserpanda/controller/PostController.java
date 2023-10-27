package architecture.lesserpanda.controller;

import architecture.lesserpanda.entity.Post;
import architecture.lesserpanda.entity.Reply;
import architecture.lesserpanda.entity.User;
import architecture.lesserpanda.dto.PostDto;
import architecture.lesserpanda.dto.ReplyDto;
import architecture.lesserpanda.service.PostService;
import architecture.lesserpanda.service.ReplyService;
import architecture.lesserpanda.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final ReplyService replyService;

    @GetMapping("/new")
    private String post(Model model, PostDto postForm){
        model.addAttribute("postForm", postForm);
        return "/post/createPost";
    }

    @PostMapping("/new")
    private String createPost(@ModelAttribute PostDto postForm){
        User user = userService.findUser(1L);
        Post post = new Post(postForm.getTitle(), postForm.getContent(), LocalDateTime.now(), postForm.getComplete());
        postService.savePost(post, user.getId());

        return "redirect:/post/postList";
    }

    @GetMapping("/postList")
    public String posts(Model model){
        List<Post> postList = postService.findAll();
        model.addAttribute("posts", postList);
        return "/post/postList";
    }

//    @GetMapping("/{postId}/viewPost")
//    public String choosePost(@PathVariable("postId") Long postId, Model model){
//        Post post = postService.findPost(postId);
//        List<Reply> replyList = replyService.findByPostId(postId);
//        ReplyDto replyForm = new ReplyDto();
//        model.addAttribute("post", post);
//        model.addAttribute("replyForm", replyForm);
//        model.addAttribute("replies", replyList);
//        return "/post/viewPost";
//    }

    @PostMapping("/{postId}/viewPost")
    public String createReply(@PathVariable("postId") Long postId, @ModelAttribute("replyForm") ReplyDto replyForm){
        Reply reply = new Reply(replyForm.getContent(), LocalDateTime.now());
        replyService.saveReply(reply, postId);
        return "redirect:/post/{postId}/viewPost";
    }
}
