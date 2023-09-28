package architecture.lesserpanda.service;

import architecture.lesserpanda.entity.User;
import architecture.lesserpanda.entity.UserStack;
import architecture.lesserpanda.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(User user){
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    public User findUser(Long id){
        return userRepository.findById(id);
    }

    private void validateDuplicateUser(User user) {
        if(!userRepository.findByLoginId(user.getLoginId()).isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
