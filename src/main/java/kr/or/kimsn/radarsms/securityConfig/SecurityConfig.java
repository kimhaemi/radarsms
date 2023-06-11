package kr.or.kimsn.radarsms.securityConfig;

import javax.servlet.DispatcherType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import kr.or.kimsn.radarsms.securityConfig.auth.AuthenticationSuccess;


@Configuration
@EnableWebSecurity // 활성화. 스프링 시큐리시 필터가 스프링 필터체인에 등록됨.
public class SecurityConfig {

    @Autowired
    private AuthenticationSuccess authenticationSuccess;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // csrf는 비활성화하고 /css, /index는 접속허용, /user/** url은 USER 권한만 가능으로 설정을 구성한다. 그리고
        // user를 생성해주고, password encoder를 bean으로 선언해준다.
        http
            .csrf(AbstractHttpConfigurer::disable) // CSRF 공격에 대한 방어를 해제 합니다.
            .cors((cors)-> 
                cors.disable()
            )
            //인증/인가
            .authorizeHttpRequests((authorizeRequests)->
                authorizeRequests
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() //JSP 등 컨트롤러에서 화면 파일명을 리턴해 화면을 바꾸는 경우
            //     // .antMatchers("/users/**").permitAll()
            //     // .antMatchers("/common/**").permitAll()
            )
            .formLogin(loginform->
                loginform
                .loginPage("/login")
                .successHandler(authenticationSuccess)
                // .defaultSuccessUrl("/index", true)
                .failureUrl("/common/error")
                .permitAll()
            )
        ;
        return http.build();
    }

    /**
     * filter
     */
    // public HttpSecurity addFilter(Filter filter){
    // Class<? extends Filter> filterClass = filter.getClass();
    // if(!comparator.isRest){

    // }
    // }

    // WebSecurityConfig 클래스에 BCryptPasswordEncoder 클래스를 Bean으로 등록해서 Controller에 의존성
    // 주입을 받아서 위와 같이 사용하면 됩니다.
    // 해당 암호화 기능을 이용하지 않는다면 아래 설명할 Spring Security의 로그인 기능을 이용할 수 없습니다.
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    // WebSecurityCustomizer를 통해 Spring Security를 적용하지 않을 리소스를 설정한다.
    /*
     * @Bean
     * public WebSecurityCustomizer webSecurityCustomizer() {
     * return (web) -> web.ignoring().antMatchers("/static/**"); // "/static/**" 으로
     * 들어오는 요청 무시
     * // return (web) -> web.ignoring().antMatchers("/images/**",
     * "/js/**","/css/**");
     * // return (web) -> web.ignoring().antMatchers("/resources/**");
     * }
     */

    // @Bean
    // public CorsConfigurationSource corsConfigurationSource() {
    //     CorsConfiguration configuration = new CorsConfiguration();
    //     configuration.setAllowCredentials(false); // 쿠키를 받을건지
    //     configuration.setAllowedOrigins(Arrays.asList("http://localhost:2023"));
    //     configuration.setAllowedMethods(Arrays.asList("GET", "POST"));

    //     configuration.addAllowedHeader("*");

    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**", configuration);
    //     return source;

    // }
}
