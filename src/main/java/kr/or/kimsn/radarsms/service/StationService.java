package kr.or.kimsn.radarsms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.kimsn.radarsms.dto.ReceiveConditionDto;
import kr.or.kimsn.radarsms.dto.ReceiveDataDto;
import kr.or.kimsn.radarsms.dto.ReceiveSettingDto;
import kr.or.kimsn.radarsms.dto.StationDto;
import kr.or.kimsn.radarsms.repository.ReceiveConditionRepository;
import kr.or.kimsn.radarsms.repository.ReceiveDataRepository;
import kr.or.kimsn.radarsms.repository.ReceiveSettingRepository;
import kr.or.kimsn.radarsms.repository.StationRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StationService {

    private final StationRepository stationRepository;
    private final ReceiveConditionRepository receiveConditionRepository;
    private final ReceiveSettingRepository receiveSettingRepository;
    private final ReceiveDataRepository receiveDataRepository;

    // 지점 데이터
    public StationDto getStationDetail(String siteCd) {
        return stationRepository.findBySiteCdOrderBySortOrder(siteCd);
    }

    // 자료 수신 처리 설정(자료 수신 처리 설정 테이블)
    public List<ReceiveSettingDto> getReceiveSetting(String dataKind) {
        return receiveSettingRepository.findByDataKind(dataKind);
    }

    public ReceiveSettingDto getReceiveSetting(String dataKind, Integer permitted_watch) {
        return receiveSettingRepository.findByDataKindAndPermittedWatch(dataKind, permitted_watch);
    }

    // 지점별 최종 수신 확인 시각(NQC)
    public List<ReceiveConditionDto> getStationLastCheck(String siteCd, String date_Type) {
        return receiveConditionRepository.findBySiteAndDataType(siteCd, date_Type);
    }

    // 최종 수신 확인 시각(NQC)
    public List<ReceiveConditionDto> getStationLastCheck(String date_Type) {
        return receiveConditionRepository.findByDataTypeOrderBySite(date_Type);
    }

    // 지점별 과거자료 검색
    public List<ReceiveDataDto> getReceiveDataList(String data_kind, String data_type, String site, String dateStart, String dateClose) {
        return receiveDataRepository.getReceiveDataList(data_kind, data_type, site, dateStart, dateClose);
    }
}
