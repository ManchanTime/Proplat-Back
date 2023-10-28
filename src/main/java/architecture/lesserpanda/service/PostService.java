package architecture.lesserpanda.service;

import architecture.lesserpanda.dto.PostDto;
import architecture.lesserpanda.dto.UserDto;
import architecture.lesserpanda.entity.*;
import architecture.lesserpanda.repository.PostRepository;
import architecture.lesserpanda.repository.SearchPostRepository;
import architecture.lesserpanda.repository.TechStackRepository;
import architecture.lesserpanda.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static architecture.lesserpanda.dto.PostDto.*;
import static architecture.lesserpanda.dto.UserDto.*;

/**
 * 기능
 * 1. 포스트 저장
 * 2. 포스트 불러오기
 * 3. 포스트 검색
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TechStackRepository techStackRepository;

    @Transactional
    public Long save(SaveRequestDto saveRequestDto, LoginResponseDto loginResponseDto){
        User user = userRepository.findById(loginResponseDto.getId());
        Post post = Post.toEntity(saveRequestDto, user);

        List<String> stackList = saveRequestDto.getStackList();
        for(String name : stackList){
            TechStack techStack = techStackRepository.findByName(name);
            PostStack postStack = PostStack.createPostStack(techStack);
            post.setPostStack(postStack);
        }
        postRepository.save(post);
        return post.getId();
    }

    public Page<PostListResponseDto> findAllPost(String keyword, Pageable pageable){
        return postRepository.postListResponseDtoPage(keyword, pageable);
    }
}
