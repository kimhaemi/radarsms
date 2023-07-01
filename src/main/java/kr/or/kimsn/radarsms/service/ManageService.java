package kr.or.kimsn.radarsms.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import kr.or.kimsn.radarsms.dto.ReceiveConditionCriteriaDto;
import kr.or.kimsn.radarsms.dto.ReceiveSettingDto;
import kr.or.kimsn.radarsms.dto.SmsSendPatternDto;
import kr.or.kimsn.radarsms.dto.SmsTargetGroupDto;
import kr.or.kimsn.radarsms.dto.SmsTargetGroupMemberDto;
import kr.or.kimsn.radarsms.dto.StationStatusDto;
import kr.or.kimsn.radarsms.repository.ReceiveConditionCriteriaRepository;
import kr.or.kimsn.radarsms.repository.ReceiveConditionRepository;
import kr.or.kimsn.radarsms.repository.ReceiveSettingRepository;
import kr.or.kimsn.radarsms.repository.SmsSendPatternRepository;
import kr.or.kimsn.radarsms.repository.SmsTargetGroupMemberRepository;
import kr.or.kimsn.radarsms.repository.SmsTargetGroupRepository;
import kr.or.kimsn.radarsms.repository.StationStatusRepository;
import kr.or.kimsn.radarsms.repository.entity.ReceiveEntity.SmsSetRcEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ManageService {

    private final StationStatusRepository stationStatusRepository;
    private final ReceiveConditionRepository receiveConditionRepository;

    private final ReceiveConditionCriteriaRepository receiveConditionCriteriaRepository;
    private final ReceiveSettingRepository receiveSettingRepository;

    private final SmsSendPatternRepository smsSendPatternRepository;
    private final SmsTargetGroupRepository smsTargetGroupRepository;
    private final SmsTargetGroupMemberRepository smsTargetGroupMemberRepository;

    /*
     * 각 지점의 현재 상태(정상운영 중인지 유지보수 상태인지...)
     */
    public List<StationStatusDto> getStationStatusList() {
        return stationStatusRepository.findAll();
    }

    // 자료 수신 상태
    public List<SmsSetRcEntity> getReceiveConditionList() {
        // List<ReceiveDto> result =
        // mapper.map(receiveConditionRepository.findReceiveConditionStationRdrReceiveSetting(),
        // ReceiveDto.class);
        return receiveConditionRepository.findReceiveConditionStationRdrReceiveSetting();
    }

    // 경고 기준 설정
    public List<ReceiveConditionCriteriaDto> getReceiveConditionCriteriaList() {
        return receiveConditionCriteriaRepository.findAll();
    }

    // 자료 수신 감시 설정
    public List<ReceiveSettingDto> getReceiveSettingList() {
        return receiveSettingRepository.findByOrderByDataKindDescPermittedWatchDesc();
    }

    // 문자 메시지 패턴
    public List<SmsSendPatternDto> getSmsSendPatternList() {
        return smsSendPatternRepository.findByOrderByCodeAscModeDesc();
    }

    // 문자 수신 그룹 관리
    public List<SmsTargetGroupDto> getSmsTargetGroupList() {
        return smsTargetGroupRepository.findAll();
    }

    // 문자 수신 그룹 그룹 멤버 관리
    public SmsTargetGroupDto getSmsTargetGroupMemberList(Long id) {
        return smsTargetGroupRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Member Not Found"));
    }

    //문자 수신 그룹 멤버
    public List<SmsTargetGroupMemberDto> getSmsTargetGroupMemberList() {
        return smsTargetGroupMemberRepository.getSmsTargetGroupMemberList();
    }

}