package kr.or.kimsn.radarsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.kimsn.radarsms.dto.ReceiveConditionDto;

public interface ReceiveConditionRepository extends JpaRepository<ReceiveConditionDto, String> {

    List<ReceiveConditionDto> findByDataTypeOrderBySite(String data_type);

    List<ReceiveConditionDto> findBySiteAndDataType(String site, String data_type);

}
