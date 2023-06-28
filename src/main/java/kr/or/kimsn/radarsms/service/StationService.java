package kr.or.kimsn.radarsms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.kimsn.radarsms.dto.ReceiveConditionDto;
import kr.or.kimsn.radarsms.dto.ReceiveSettingDto;
import kr.or.kimsn.radarsms.dto.StationDto;
import kr.or.kimsn.radarsms.repository.ReceiveConditionRepository;
import kr.or.kimsn.radarsms.repository.ReceiveSettingRepository;
import kr.or.kimsn.radarsms.repository.StationRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StationService {

    private final StationRepository stationRepository;
    private final ReceiveConditionRepository receiveConditionRepository;
    private final ReceiveSettingRepository receiveSettingRepository;
    
    //지점 데이터
    public List<StationDto> getStationDetail (String siteCd) {
        return stationRepository.findBySiteCd(siteCd);
    }

    //자료 수신 처리 설정
    public List<ReceiveSettingDto> getReceiveSetting (String dataKind){
        return receiveSettingRepository.findByDataKind(dataKind);
    }

    //최종 수신 확인 시각(NQC)
    public List<ReceiveConditionDto> getStationLastCheck (String siteCd, String date_Type) {
        return receiveConditionRepository.findBySiteAndDataType(siteCd, date_Type);
    }
}
