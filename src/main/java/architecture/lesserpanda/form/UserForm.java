package architecture.lesserpanda.form;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.util.StringUtils;

@Getter @Setter
public class UserForm {

    @NotNull(message = "유저 이름은 NULL일 수 없습니다!")
    private String name;
    private String password;
    private String email;
}
