package kr.or.kimsn.radarsms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import kr.or.kimsn.radarsms.dto.ReceiveConditionCriteriaDto;
import kr.or.kimsn.radarsms.dto.ReceiveConditionDto;
import kr.or.kimsn.radarsms.dto.ReceiveDto;
import kr.or.kimsn.radarsms.dto.ReceiveSettingDto;
import kr.or.kimsn.radarsms.dto.SmsSendPatternDto;
import kr.or.kimsn.radarsms.dto.SmsSetRcDto;
import kr.or.kimsn.radarsms.dto.SmsTargetGroupDto;
import kr.or.kimsn.radarsms.dto.SmsTargetGroupLinkDto;
import kr.or.kimsn.radarsms.dto.SmsTargetGroupMemberDto;
import kr.or.kimsn.radarsms.dto.SmsTargetMemberDto;
import kr.or.kimsn.radarsms.dto.StationStatusDto;
import kr.or.kimsn.radarsms.repository.ReceiveConditionCriteriaRepository;
import kr.or.kimsn.radarsms.repository.ReceiveConditionRepository;
import kr.or.kimsn.radarsms.repository.ReceiveRepository;
import kr.or.kimsn.radarsms.repository.ReceiveSettingRepository;
import kr.or.kimsn.radarsms.repository.SmsSendPatternRepository;
import kr.or.kimsn.radarsms.repository.SmsSetRcRepository;
import kr.or.kimsn.radarsms.repository.SmsTargetGroupLinkRepository;
import kr.or.kimsn.radarsms.repository.SmsTargetGroupMemberRepository;
import kr.or.kimsn.radarsms.repository.SmsTargetGroupRepository;
import kr.or.kimsn.radarsms.repository.SmsTargetMemberRepository;
import kr.or.kimsn.radarsms.repository.StationStatusRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ManageService {

    private final StationStatusRepository stationStatusRepository;

    private final ReceiveConditionCriteriaRepository receiveConditionCriteriaRepository;
    private final ReceiveSettingRepository receiveSettingRepository;

    private final SmsSendPatternRepository smsSendPatternRepository;
    private final SmsTargetGroupRepository smsTargetGroupRepository;
    private final SmsTargetGroupMemberRepository smsTargetGroupMemberRepository;

    private final SmsSetRcRepository smsSetRcRepository;
    private final SmsTargetMemberRepository smsTargetMemberRepository;
    private final SmsTargetGroupLinkRepository smsTargetGroupLinkRepository;

    private final ReceiveRepository receiveRepository;

    /*
     * 각 지점의 현재 상태(정상운영 중인지 유지보수 상태인지...)
     */
    public List<StationStatusDto> getStationStatusList() {
        return stationStatusRepository.findAll();
    }

    // 자료 수신 상태
    public List<SmsSetRcDto> getReceiveConditionList() {
        // List<ReceiveDto> result =
        // mapper.map(receiveConditionRepository.findReceiveConditionStationRdrReceiveSetting(),
        // ReceiveDto.class);
        return smsSetRcRepository.findReceiveConditionStationRdrReceiveSetting();
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

    //그룹관리 > 그룹 감시 자료 설정
    public List<SmsTargetGroupLinkDto> getTableJoinAll(Long id){
        return smsTargetGroupLinkRepository.getTableJoinAll(id);
    }

    //그룹관리 > 그룹 감시 자료 설정 되지 않음
    public List<ReceiveDto> getSmsTargetGroupNotLink(List<SmsTargetGroupLinkDto> links){
        List<ReceiveDto> rclist = receiveRepository.getReceiveTableJoin();
        List<ReceiveDto> rmlist = new ArrayList<ReceiveDto>();

        for (SmsTargetGroupLinkDto link : links) {
        for (ReceiveDto rc : rclist) {
            if (link.getData_kind().equals(rc.getData_kind()) && link.getSite().equals(rc.getSite()) && 
                link.getData_type().equals(rc.getData_type())) {
                rmlist.add(rc);
                break;
                } 
            } 
        } 
        if (rmlist.size() > 0) {
            for (ReceiveDto rm : rmlist) {
                rclist.remove(rm);
            }
        }

        return rclist;
    }

    // 문자 수신 그룹 관리
    public List<SmsTargetGroupDto> getSmsTargetGroupList() {
        return smsTargetGroupRepository.findAll();
    }

    // 문자 수신 그룹 > [ admin ] 그룹 관리
    public SmsTargetGroupDto getSmsTargetGroupId(Long id) {
        return smsTargetGroupRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Member Not Found"));
    }

    //문자 수신 그룹 멤버 
    public List<SmsTargetGroupMemberDto> getSmsTargetGroupMemberList(Long id) {

        return smsTargetGroupMemberRepository.getSmsTargetGroupMemberList();
    }

    //문자 수신 그룹 멤버 ([ admin ] 그룹에 속한 사용자)
    public List<SmsTargetGroupMemberDto> getSmsTargetGroupsMemberId(Long id) {
        return smsTargetGroupMemberRepository.getSmsTargetGroupsMemberId(id);
    }

    //문자 수신 그룹 멤버 ([ admin ] 그룹에 속하지 않은 사용자)
    public List<SmsTargetGroupMemberDto> getSmsTargetGroupsMemberIdNot(List<SmsTargetGroupMemberDto> links) {
        List<SmsTargetGroupMemberDto> mlist = smsTargetGroupMemberRepository.getSmsTargetGroupMemberList();
        List<SmsTargetGroupMemberDto> rmlist = new ArrayList<SmsTargetGroupMemberDto>();
        for (SmsTargetGroupMemberDto link : links) {
          for (SmsTargetGroupMemberDto m : mlist) {
            if (link.getMid() == m.getMid()) {
              rmlist.add(m);
              break;
            } 
          } 
        } 
        if (rmlist.size() > 0) {
          for (SmsTargetGroupMemberDto rm : rmlist) {
            mlist.remove(rm);
          }
        }
        return mlist;
    }


    //문자 수신자 관리
    public List<SmsTargetMemberDto> getSmsTargetMemberList(){
        return smsTargetMemberRepository.findByOrderByName();
    }

}