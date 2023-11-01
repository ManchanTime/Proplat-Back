package architecture.lesserpanda.service;

import architecture.lesserpanda.dto.PostDto;
import architecture.lesserpanda.dto.TechStackDto;
import architecture.lesserpanda.entity.*;
import architecture.lesserpanda.exception.PostNotFoundException;
import architecture.lesserpanda.repository.PostRepository;
import architecture.lesserpanda.repository.TechStackRepository;
import architecture.lesserpanda.repository.UserRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static architecture.lesserpanda.dto.PostDto.*;
import static architecture.lesserpanda.dto.TechStackDto.*;
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
    public Long save(SaveRequestDto saveRequestDto, LoginResponseDto loginResponseDto) {
        User user = userRepository.findById(loginResponseDto.getId()).orElseThrow();
        Post post = Post.toEntity(saveRequestDto, user);

        List<String> stackList = saveRequestDto.getStackList();
        for (String name : stackList) {
            TechStack techStack = techStackRepository.findByName(name);
            PostStack postStack = PostStack.createPostStack(techStack);
            post.setPostStack(postStack);
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
            throw new PostNotFoundException("존재하지 않는 포스트입니다.");
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
                throw new IllegalStateException("작성자가 아닙니다.");
            }
        }
        throw new PostNotFoundException("존재하지 않는 포스트입니다.");
    }
}
