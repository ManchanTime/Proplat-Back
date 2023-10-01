package architecture.lesserpanda.repository;

import architecture.lesserpanda.entity.Reply;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyRepository {

    private final EntityManager em;

    public void save(Reply reply){
        em.persist(reply);
    }

    public Reply findById(Long id){
        return em.find(Reply.class, id);
    }

    public List<Reply> findByPostId(Long postId){
        return em.createQuery("select r from Reply r join r.post p where p.id = :postId", Reply.class)
                .setParameter("postId", postId)
                .getResultList();
    }

    public void delete(Reply reply){
        em.remove(reply);
    }

    public List<Reply> findAll(){
        return em.createQuery("select r from Reply r", Reply.class).getResultList();
    }
}
