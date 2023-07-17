package kr.or.kimsn.radarsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import kr.or.kimsn.radarsms.dto.ReceiveConditionCriteriaDto;

public interface ReceiveConditionCriteriaRepository extends JpaRepository<ReceiveConditionCriteriaDto, String> {

    @Query(
        nativeQuery = true,
        value=
        "update receive_condition_criteria set \n"+
        "    criterion = :criterion \n"+
        "where 1=1\n"+
        "  and code = :code \n"
    )
    @Transactional
    @Modifying
    // 경고 기준 설정 일괄 수정
    Integer receiveConditionCriteriaModify(
        @Param("criterion") String criterion,
        @Param("code") String code
    );
    
}
