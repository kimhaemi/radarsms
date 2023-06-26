package kr.or.kimsn.radarsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.kimsn.radarsms.dto.UsersDto;

//CRUD 함수를 JpaRepository가 들고있음.
// @repository라는 어노테이션이 없어도 Ioc가 됨.
//이유는 JpaRepository를 상속했기 때문에 가능
//@Bean으로 자동 등록 됨
public interface UsersRepository extends JpaRepository<UsersDto, Long> {

    // findBy는 규칙, Username은 문법
    // select * from user where username = ?
    UsersDto findByUsername(String username); // jpa query method

}
