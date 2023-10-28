package architecture.lesserpanda.repository;

import architecture.lesserpanda.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static architecture.lesserpanda.dto.PostDto.*;

public interface SearchPostRepository {

    Page<PostListResponseDto> postListResponseDtoPage(String keyword, Pageable pageable);
}
