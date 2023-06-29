package kr.or.kimsn.radarsms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.kimsn.radarsms.dto.ReceiveConditionCriteriaDto;
import kr.or.kimsn.radarsms.dto.ReceiveDto;
import kr.or.kimsn.radarsms.dto.StationStatusDto;
import kr.or.kimsn.radarsms.repository.ReceiveConditionCriteriaRepository;
import kr.or.kimsn.radarsms.repository.ReceiveConditionRepository;
import kr.or.kimsn.radarsms.repository.StationStatusRepository;
import kr.or.kimsn.radarsms.repository.entity.ReceiveEntity.SmsSetRcEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ManageService {

    private final StationStatusRepository stationStatusRepository;
    private final ReceiveConditionRepository receiveConditionRepository;

    private final ReceiveConditionCriteriaRepository receiveConditionCriteriaRepository;

    /*
    * 각 지점의 현재 상태(정상운영 중인지 유지보수 상태인지...)
    */
    public List<StationStatusDto> getStationStatusList () {
        return stationStatusRepository.findAll();
    }

    public List<SmsSetRcEntity> getReceiveConditionList () {
        // List<ReceiveDto> result = mapper.map(receiveConditionRepository.findReceiveConditionStationRdrReceiveSetting(), ReceiveDto.class);
        return receiveConditionRepository.findReceiveConditionStationRdrReceiveSetting();
    }

    public List<ReceiveConditionCriteriaDto> getReceiveConditionCriteriaList () {
        return receiveConditionCriteriaRepository.findAll();
    }
    
}
