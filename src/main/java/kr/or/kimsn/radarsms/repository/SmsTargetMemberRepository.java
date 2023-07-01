package kr.or.kimsn.radarsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.kimsn.radarsms.dto.SmsTargetMemberDto;

public interface SmsTargetMemberRepository extends JpaRepository<SmsTargetMemberDto, Long>{
    
    List<SmsTargetMemberDto> findAll();

    List<SmsTargetMemberDto> findByOrderByName();
}
