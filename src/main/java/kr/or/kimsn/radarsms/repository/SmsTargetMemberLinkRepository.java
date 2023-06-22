package kr.or.kimsn.radarsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.kimsn.radarsms.dto.SmsTargetMemberLinkDto;

public interface SmsTargetMemberLinkRepository extends JpaRepository<SmsTargetMemberLinkDto, Long>{
    
    List<SmsTargetMemberLinkDto> findAll();
}
