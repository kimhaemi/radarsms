package kr.or.kimsn.radarsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.kimsn.radarsms.dto.MembersDto;

public interface MembersRepository extends JpaRepository<MembersDto, Long> {
    List<MembersDto> findAll();
    
}
