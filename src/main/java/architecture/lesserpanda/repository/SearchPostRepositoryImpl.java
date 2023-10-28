package architecture.lesserpanda.repository;

import architecture.lesserpanda.dto.PostDto;
import architecture.lesserpanda.dto.PostStackDto;
import architecture.lesserpanda.dto.TechStackDto;
import architecture.lesserpanda.entity.Post;
import architecture.lesserpanda.entity.QPost;
import architecture.lesserpanda.entity.QPostStack;
import architecture.lesserpanda.entity.QTechStack;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static architecture.lesserpanda.dto.PostDto.*;
import static architecture.lesserpanda.dto.PostStackDto.*;
import static architecture.lesserpanda.dto.TechStackDto.*;
import static architecture.lesserpanda.entity.QPost.*;
import static architecture.lesserpanda.entity.QPostStack.*;
import static architecture.lesserpanda.entity.QTechStack.*;
import static com.querydsl.core.group.GroupBy.*;

@Repository
public class SearchPostRepositoryImpl extends QuerydslRepositorySupport implements SearchPostRepository {

    private final JPAQueryFactory jpaQueryFactory;
    public SearchPostRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Post.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<PostListResponseDto> postListResponseDtoPage(String keyword, Pageable pageable){
        List<PostListResponseDto> content = jpaQueryFactory
                .select(
                        Projections.fields(
                                PostListResponseDto.class,
                                post.id.as("postId"),
                                post.title.as("title"),
                                post.content.as("content"),
                                post.complete.as("complete"),
                                list(
                                        Projections.fields(
                                                ResponseDto.class,
                                                postStack.techStack.name.as("name"),
                                                postStack.techStack.type.as("type")
                                        )
                                ).as("stackList")
                        )
                )
                .from(post)
                .join(post.postStackList, postStack)
                .join(postStack.techStack, techStack)
                .where(containTitle(keyword), containContent(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long count  = content.size();
        return new PageImpl<>(content, pageable, count);
    }

    BooleanExpression containContent(String keyword){
        if(keyword == null || keyword.isEmpty())
            return null;
        else return post.content.contains(keyword);
    }

    BooleanExpression containTitle(String keyword){
        if(keyword == null || keyword.isEmpty())
            return null;
        else return post.title.contains(keyword);
    }
}
