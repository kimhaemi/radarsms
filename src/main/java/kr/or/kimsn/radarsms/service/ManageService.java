package kr.or.kimsn.radarsms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.kimsn.radarsms.dto.ReceiveConditionDto;
import kr.or.kimsn.radarsms.dto.ReceiveDto;
import kr.or.kimsn.radarsms.dto.StationStatusDto;
import kr.or.kimsn.radarsms.repository.ReceiveConditionRepository;
import kr.or.kimsn.radarsms.repository.StationStatusRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ManageService {

    private final StationStatusRepository stationStatusRepository;
    private final ReceiveConditionRepository receiveConditionRepository;

    /*
    * 각 지점의 현재 상태(정상운영 중인지 유지보수 상태인지...)
    */
    public List<StationStatusDto> getStationStatusList () {
        return stationStatusRepository.findAll();
    }

    public List<ReceiveDto> getReceiveConditionList () {
        return receiveConditionRepository.findReceiveConditionStationRdrReceiveSetting();
    }
    
}
