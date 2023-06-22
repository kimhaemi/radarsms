package kr.or.kimsn.radarsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.kimsn.radarsms.dto.ReceiveConditionCriteriaDto;

public interface ReceiveConditionCriteriaRepository extends JpaRepository<ReceiveConditionCriteriaDto, Long> {
    List<ReceiveConditionCriteriaDto> findAll();    
}
