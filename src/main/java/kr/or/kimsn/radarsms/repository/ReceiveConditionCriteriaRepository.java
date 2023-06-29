package kr.or.kimsn.radarsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.kimsn.radarsms.dto.ReceiveConditionCriteriaDto;

public interface ReceiveConditionCriteriaRepository extends JpaRepository<ReceiveConditionCriteriaDto, String> {
    
}
