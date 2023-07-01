package kr.or.kimsn.radarsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.kimsn.radarsms.dto.SmsTargetGroupDto;

public interface SmsTargetGroupRepository extends JpaRepository<SmsTargetGroupDto, Long> {

    // List<SmsTargetGroupDto> findByGid(Long gid);
    List<SmsTargetGroupDto> findByIdLessThan(Long gid);
    
}
