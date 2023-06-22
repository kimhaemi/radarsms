package kr.or.kimsn.radarsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.kimsn.radarsms.dto.SmsTargetGroupLinkDto;

public interface SmsTargetGroupLinkRepository extends JpaRepository<SmsTargetGroupLinkDto, Long>{
    
    List<SmsTargetGroupLinkDto> findAll();
}
