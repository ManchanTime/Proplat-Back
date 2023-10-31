package architecture.lesserpanda.repository;

import architecture.lesserpanda.dto.ClubDto;
import architecture.lesserpanda.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static architecture.lesserpanda.dto.ReplyDto.*;
import static architecture.lesserpanda.dto.UserDto.*;

@Repository
public interface FindNoSearchRepository {


    Page<ReplyGetResponseDto> findPostReplies(Long postId, Pageable pageable);

    Page<ClubDto> clubListResponseDtoPage(Pageable pageable);

    UserInfoDto findUserInfo(Long userId);
}

