package kr.or.kimsn.radarsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.kimsn.radarsms.dto.SmsSendPatternDto;
import kr.or.kimsn.radarsms.dto.pkColumn.SmsSendPatternPk;

public interface SmsSendPatternRepository extends JpaRepository<SmsSendPatternDto, SmsSendPatternPk>{
    List<SmsSendPatternDto> findAll();

    List<SmsSendPatternDto> findByOrderByCodeAscModeDesc();
}
