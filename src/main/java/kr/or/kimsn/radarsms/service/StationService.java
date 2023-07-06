package kr.or.kimsn.radarsms.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

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

    // 지점 데이터
    public List<StationDto> getStationDetail(String siteCd) {
        return stationRepository.findBySiteCdOrderBySortOrder(siteCd);
    }

    // 자료 수신 처리 설정(자료 수신 처리 설정 테이블)
    public List<ReceiveSettingDto> getReceiveSetting(String dataKind) {
        return receiveSettingRepository.findByDataKind(dataKind);
    }

    public List<ReceiveSettingDto> getReceiveSetting(String dataKind, Integer permitted_watch) {
        // select * from receive_setting where permitted_watch = 1 and data_kind =
        // #data_kind#
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

    // 과거자료
    // public Map<String, ReceiveDataVO> getReceiveData(String kind, String site,
    // List<ReceiveSettingVO> receiveSetting, Date now) {
    // Map<String, ReceiveDataVO> map = new HashMap<String, ReceiveDataVO>();
    // for (ReceiveSettingVO rsets : receiveSetting) {
    // ReceiveDataVO rdSet = new ReceiveDataVO();
    // if (rsets.getTime_zone().equals("U")) {
    // rdSet.setData_time(DateUtils.addHours(now, -9));
    // } else {
    // rdSet.setData_time(now);
    // }
    // rdSet.setData_kind(rsets.getData_kind());
    // rdSet.setData_type(rsets.getData_type());
    // rdSet.setSite(site);

    // List<ReceiveDataVO> dataList = this.receiveDao.getReceiveDataList(rdSet);
    // for (ReceiveDataVO vo : dataList) {
    // String key = String.valueOf(vo.getData_type()) + "#" +
    // DateUtil.formatDate("yyyy.MM.dd_HH:mm", vo.getData_time());
    // map.put(key, vo);
    // }
    // }

    // return map;

}
