package kr.or.kimsn.radarsms.controller;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.or.kimsn.radarsms.dto.MembersDto;
import kr.or.kimsn.radarsms.service.MembersService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LoginController {

    private final MembersService membersService;
    private final PasswordEncoder passwordEncoder;

    // 로그인 화면
    @GetMapping({ "/login" })
    public String login() {
        return "views/login";
    }

    // 로그인 화면
    @PostMapping({ "/loginProc" })
    public String loginProc(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
        System.out.println("dfsdfsdfsdfsdfsdfsfsdf");
        HttpSession session = req.getSession();
        String userId = req.getParameter("userId");
        String password = req.getParameter("pwd");

        MembersDto user = membersService.getUserId(userId);
        System.out.println("user :::: " + user);

        if( user == null ){
            System.out.println("durlsi");
            model.addAttribute("error", "등록된 사용자가 없습니다.");
            return "views/login";
        }
        
        byte[] encodedBytes = Base64.getEncoder().encode(password.getBytes());
        String encodedText = new String(encodedBytes);
        System.out.println("Encoded Text: " + encodedText);

        // byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
        // String decodedText = new String(decodedBytes);
        // System.out.println("Decoded Text: " + decodedText);

        byte[] decodedBytes = Base64.getDecoder().decode(user.getPassword());
        String decodedText = new String(decodedBytes);
        System.out.println("Decoded Text: " + decodedText);

        // CryptUtil crypt = CryptUtil.getInstance();
        // String enPassword = CryptUtil.hasingToB64(user.getPassword());

        System.out.println("user.getPassword() ::::" + user.getPassword());

        // CryptUtil crypt = CryptUtil.getInstance();
        
        
        // password = passwordEncoder.encode(password);
        // if(password != null && passwordEncoder.matches(password, user.getPassword())){
        //     System.out.println("passworderror");
        //     model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
        //     return "views/login";
        // }

        session.setAttribute("loginUser", user);
        return "views/login";
    }

    // 로그 아웃
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        System.out.println("logout===========================");
        HttpSession session = request.getSession();
        session.invalidate();

        System.out.println("session :: " + session);

        return "views/login";
    }

    //error page
    @GetMapping("/error")
    public String error(){
        System.out.println("dfsfsdfsfsdfsdfsdfsdfsfsdf");
        return "views/common/error";
    }
}
