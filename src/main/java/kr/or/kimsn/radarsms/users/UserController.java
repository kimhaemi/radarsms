package kr.or.kimsn.radarsms.users;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserRepository userReository;

    // @Autowired
    // private BCryptPasswordEncoder bCryptPasswordEncoder;

    // @GetMapping({ "", "/"})
    // public String login() {
    // return "loginForm";
    // }

    // // login 후
    // @GetMapping("/index")
    // public String index() {
    //     return "index";
    // }

    @GetMapping("/logout")
    public String logout() {
        System.out.println("logout===========================");
        return "views/logout";
    }

    // 로그인 화면
    @GetMapping({ "/login" })
    public String login() {
        return "views/users/loginForm";
    }

    // 사용자 등록 화면
    @GetMapping("/users/joinForm")
    public String join() {
        return "views/users/joinForm";
    }

    // 사용자 관리
    @GetMapping("/users/admin_user")
    public ModelAndView admin_user() {
        ModelAndView mav = new ModelAndView();
        System.out.println("ffsdfsdfsdfsdfsdfsd");
        
        mav.setViewName("views/users/admin_user");
        return mav;
    }

    // 사용자 등록 화면
    @GetMapping("/users/admin_user_form")
    public ModelAndView admin_user_form() {
        ModelAndView mav = new ModelAndView();
        System.out.println("ffsdfsdfsdfsdfsdfsd");
        
        mav.setViewName("views/users/admin_user_form");
        return mav;
    }

    // 사용자 등록
    /* @PostMapping("/join")
    public String join(UserDto user) {
        System.out.println(user);
        // 비밀번호 암호화
        String rawPwd = user.getPassword();
        String encPwd = bCryptPasswordEncoder.encode(rawPwd);
        user.setPassword(encPwd);
        // 임의로 넣어줌.
        user.setRoles("ROLE_USER");
        userReository.save(user);
        return "redirect:/login";
    } */

    @GetMapping("/joinProc")
    public @ResponseBody String joinProc() {
        return "가입 완료";
    }

    // 에러
    @GetMapping("/common/error")
    public String error() {
        System.out.println("errordfsdfsdfsd");
        return "views/common/error";
    }

    // @PostMapping("/loginProc")
    // public String loginProc(@RequestParam("username") String username,
    // @RequestParam("password") String password,
    // Model model) {
    // model.addAttribute("username", username);
    // model.addAttribute("password", password);
    // return "redirect:/";
    // }
}