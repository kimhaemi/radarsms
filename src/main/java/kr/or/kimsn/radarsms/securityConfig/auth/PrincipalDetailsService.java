package kr.or.kimsn.radarsms.securityConfig.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.or.kimsn.radarsms.dto.UserDto;
import kr.or.kimsn.radarsms.repository.UserRepository;

//시큐리티 설정에서 loginProcessingUrl("/login");
//login 요청이 오면 자동으로 UserDetailsService 타입으로 Ioc 되어있는 loadUserByUsername 함수가 실행됨.(규칙임)
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // return된 값이 시큐리티 session(내부 Authentication(내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username: " + username);
        // user가 있는지 확인
        UserDto userEntity = userRepository.findByUsername(username);

        System.out.println("userEntity : " + userEntity);

        if (userEntity != null) {
            return new PrincipalDetails(userEntity);
        }

        return null;
    }

}
