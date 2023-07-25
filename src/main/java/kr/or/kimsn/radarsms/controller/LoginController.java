package kr.or.kimsn.radarsms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LoginController {

    // 로그인 화면
    @GetMapping({ "/login" })
    public String login() {
        return "views/login";
    }

    // 로그인 proc
    @GetMapping({ "/loginProc" })
    public String loginProc() {
        return "/";
    }

    // 로그 아웃
    @GetMapping("/logout")
    public String logout() {
        System.out.println("logout===========================");
        return "views/logout";
    }
}
