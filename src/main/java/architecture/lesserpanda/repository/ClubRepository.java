package architecture.lesserpanda.repository;

import architecture.lesserpanda.entity.Club;
import architecture.lesserpanda.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long>, FindNoSearchRepository {

}
