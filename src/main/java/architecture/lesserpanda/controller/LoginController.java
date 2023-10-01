package architecture.lesserpanda.controller;

import architecture.lesserpanda.entity.User;
import architecture.lesserpanda.form.UserForm;
import architecture.lesserpanda.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("userForm", new UserForm());
        return "login";
    }

    /**
     * DB에 유저 정보가 있는지 확인
     */
    @PostMapping("/login")
    public String checkUser(@ModelAttribute UserForm userForm){
        if(userService.checkUser(userForm.getEmail(), userForm.getPassword())){
            return "post/postList";
        }
        else return "login";
    }

    @GetMapping("/signup")
    public String signUp(Model model){
        model.addAttribute("userForm", new UserForm());
        return "signup";
    }

    @PostMapping("/signup")
    public String joinUser(@ModelAttribute UserForm userForm){
        User user = new User(userForm.getName(), userForm.getEmail(), userForm.getPassword());
        userService.join(user);
        return "redirect:/login";
    }
}
