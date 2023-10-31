package architecture.lesserpanda.repository;

import architecture.lesserpanda.entity.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, FindNoSearchRepository{
    Optional<User> findByLoginId(String longId);
}
