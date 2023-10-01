package architecture.lesserpanda.service;

import architecture.lesserpanda.entity.User;
import architecture.lesserpanda.entity.UserStack;
import architecture.lesserpanda.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public Long join(User user){
        validateDuplicateUser(user);
        String encode = passwordEncoder.encode(user.getLoginPassword());
        user.setPassword(encode);
        userRepository.save(user);
        return user.getId();
    }

    public User findUser(Long id){
        return userRepository.findById(id);
    }

    public List<User> findByUserLoginId(String loginId){
        return userRepository.findByLoginId(loginId);
    }

    /**
     * 리포지토리에서 받아온 비밀번호와 입력한 비밀번호를 match 함수를 통해 비교 후 맞으면 true 아니면 false
     */
    public boolean checkUser(String loginId, String loginPassword){
        String pwd = userRepository.checkUser(loginId);
        return passwordEncoder.matches(loginPassword, pwd);
    }

    private void validateDuplicateUser(User user) {
        if(!userRepository.findByLoginId(user.getLoginId()).isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
