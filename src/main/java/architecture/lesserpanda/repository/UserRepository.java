package architecture.lesserpanda.repository;

import architecture.lesserpanda.entity.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user){
        em.persist(user);
    }

    public User findById(Long id){
        return em.find(User.class, id);
    }

    public List<User> findByLoginId(String loginId){
        return em.createQuery("select u from User u where u.loginId = :loginId", User.class)
                .setParameter("loginId", loginId)
                .getResultList();
    }

    /**
     *  유저 이메일을 받아서 값을 꺼낸 후 암호화된 비밀번호 return
     */
    public String checkUser(String loginId){
        List<User> user =
                em.createQuery("select u from User u where u.loginId = :loginId", User.class)
                .setParameter("loginId", loginId)
                .getResultList();
        if(!user.isEmpty())
            return user.get(0).getLoginPassword();
        else return "";
    }
}
