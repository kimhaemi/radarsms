package kr.or.kimsn.radarsms.config.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccess implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // AuthenticationException 발생 시 사용자의 요청 정보가 담긴 캐시가 세션에 저장됨 -> 로그인 성공 시 redirect
        // RequestCache requestCache = new HttpSessionRequestCache();
        // SavedRequest savedRequest = requestCache.getRequest(request, response);

        // String redirectUrl = savedRequest.getRedirectUrl();

        System.out.println("loginsuccess");
        response.sendRedirect("/");
    }
    
}