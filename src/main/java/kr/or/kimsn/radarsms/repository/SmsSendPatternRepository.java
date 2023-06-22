package kr.or.kimsn.radarsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.kimsn.radarsms.dto.SmsSendPatternDto;

public interface SmsSendPatternRepository extends JpaRepository<SmsSendPatternDto, Long>{
    List<SmsSendPatternDto> findAll();
}
