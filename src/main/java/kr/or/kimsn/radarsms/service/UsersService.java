package kr.or.kimsn.radarsms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.kimsn.radarsms.dto.UsersDto;
import kr.or.kimsn.radarsms.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
// @DependsOn("passwordEncoder")
public class UsersService {
    
    private final UsersRepository usersRepository;

    //사용자 조회
    public List<UsersDto> getUsersList(){
        return usersRepository.findAll();
    }

    //사용자 등록
    @Transactional
    public UsersDto userAdd (UsersDto usersDto) {
        try {
            System.out.println("사용자 등록 userAdd");
            usersDto = usersRepository.save(usersDto);
        } catch (Exception e) {
            System.out.println("insert error : " +e);
        }
        return usersDto;
    }

    //사용자 수정
    @Transactional
    public UsersDto userModify (UsersDto usersDto) {
        try {
            System.out.println("사용자 수정 usermodify");
            usersDto = usersRepository.save(usersDto);
        } catch (Exception e) {
            System.out.println("insert error : " +e);
        }
        return usersDto;
    }

    //사용자 삭제
    @Transactional
    public void userDelete (Long userId) {
        try {
            System.out.println("사용자 삭제 userDelete");
            usersRepository.deleteById(userId);
        } catch (Exception e) {
            System.out.println("delete error : " +e);
        }
    }
}
