package architecture.lesserpanda.service;

import architecture.lesserpanda.entity.*;
import architecture.lesserpanda.exception.PostNotFoundException;
import architecture.lesserpanda.exception.UserNotFoundException;
import architecture.lesserpanda.repository.PostRepository;
import architecture.lesserpanda.repository.PostStackRepository;
import architecture.lesserpanda.repository.TechStackRepository;
import architecture.lesserpanda.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static architecture.lesserpanda.dto.PostDto.*;
import static architecture.lesserpanda.dto.UserDto.*;
import static architecture.lesserpanda.global.ExceptionStatement.*;

/**
 * 기능
 * 1. 포스트 저장
 * 2. 포스트 불러오기
 * 3. 포스트 검색
 * 4. 포스트 수정
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TechStackRepository techStackRepository;
    private final PostStackRepository postStackRepository;

    @Transactional
    public Long save(SaveRequestDto saveRequestDto, LoginResponseDto loginResponseDto) {
        User user = userRepository.findById(loginResponseDto.getId())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        Post post = Post.toEntity(saveRequestDto, user);

        List<String> stackList = saveRequestDto.getStackList();
        for (String name : stackList) {
            TechStack techStack = techStackRepository.findByName(name);
            PostStack postStack = PostStack.createPostStack(techStack, post);
            post.getPostStackList().add(postStack);
        }
        postRepository.save(post);
        return post.getId();
    }

    public Page<FindPostResponseDto> findAllPost(String keyword, Pageable pageable) {
        return postRepository.postListResponseDtoPage(keyword, pageable);
    }

    public FindPostResponseDto findPostById(Long postId) {
        FindPostResponseDto postById = postRepository.findOnePost(postId);
        if(postById == null){
            throw new PostNotFoundException(POST_NOT_FOUND);
        }
        return postById;
    }

    @Transactional
    public String deletePost(Long postId, String loginId) {
        Optional<Post> find = postRepository.findById(postId);
        if(find.isPresent()){
            Post post = find.get();
            if (post.getUser().getLoginId().equals(loginId)) {
                postRepository.delete(post);
                return "clear";
            } else {
                throw new IllegalStateException(NOT_OWNER);
            }
        }
        throw new PostNotFoundException(POST_NOT_FOUND);
    }

    //업데이트
    @Transactional
    public void updatePost(Long postId, SaveRequestDto saveRequestDto, Long loginId) {
        userRepository.findById(loginId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));

        List<String> techList = saveRequestDto.getStackList();
        List<PostStack> postStackList = postStackRepository.findByPost(post);
        postStackRepository.deleteAll(postStackList);

        postStackList.clear();
        for (String name : techList) {
            TechStack techStack = techStackRepository.findByName(name);
            PostStack postStack = PostStack.createPostStack(techStack, post);
            postStackList.add(postStack);
        }
        post.change(saveRequestDto, postStackList);
    }
}
