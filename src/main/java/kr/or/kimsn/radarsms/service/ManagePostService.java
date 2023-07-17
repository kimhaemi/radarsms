package kr.or.kimsn.radarsms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.kimsn.radarsms.dto.ReceiveConditionCriteriaDto;
import kr.or.kimsn.radarsms.dto.ReceiveSettingDto;
import kr.or.kimsn.radarsms.dto.SmsSendPatternDto;
import kr.or.kimsn.radarsms.dto.SmsSetRcDto;
import kr.or.kimsn.radarsms.dto.StationStatusDto;
import kr.or.kimsn.radarsms.repository.ReceiveConditionCriteriaRepository;
import kr.or.kimsn.radarsms.repository.ReceiveConditionRepository;
import kr.or.kimsn.radarsms.repository.ReceiveSettingRepository;
import kr.or.kimsn.radarsms.repository.SmsSendPatternRepository;
import kr.or.kimsn.radarsms.repository.StationStatusRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ManagePostService {

    private final ReceiveConditionRepository receiveConditionRepository;
    private final ReceiveConditionCriteriaRepository receiveConditionCriteriaRepository;
    private final ReceiveSettingRepository receiveSettingRepository;
    private final SmsSendPatternRepository smsSendPatternRepository;
    private final StationStatusRepository stationStatusRepository;

    // 지점/자료별 문자 발송 설정 일괄 수정
    public Integer receiveConditionModify (List<SmsSetRcDto> req){
        Integer result = 0;
        try {
            for(SmsSetRcDto dto : req){
                int sms_send_activation = dto.getSms_send_activation();
                String data_kind = dto.getDataKind();
                String site = dto.getSite();
                String dataType = dto.getDataType();
                System.out.println("sms_send_activation : " + sms_send_activation);
                System.out.println("data_kind : " + data_kind);
                System.out.println("site : " + site);
                System.out.println("dataType : " + dataType);
                receiveConditionRepository.receiveConditionModify(sms_send_activation, data_kind, site, dataType);
                result++;
            }
        } catch (Exception e) {
            System.out.println("insert error : " +e);
        }
        return result;
    }

    // 경고 기준 설정 일괄 수정
    public Integer receiveConditionCriteriaModify (List<ReceiveConditionCriteriaDto> receiveConditionCriteriaDto){
        Integer result = 0;
        try {
            for(ReceiveConditionCriteriaDto dto : receiveConditionCriteriaDto){
                String code = dto.getCode();
                String criterion = dto.getCriterion();
                receiveConditionCriteriaRepository.receiveConditionCriteriaModify(criterion, code);
                result++;
            }
        } catch (Exception e) {
            System.out.println("insert error : " +e);
        }
        return result;
    }

    //자료 수신 감시 설정 일괄 수정
    public Integer receiveSettingModify (List<ReceiveSettingDto> receiveConditionCriteriaDto){
        Integer result = 0;
        try {
            for( ReceiveSettingDto dto : receiveConditionCriteriaDto){
                String time_zone = dto.getTime_zone();
                String filename_pattern = dto.getFilename_pattern();
                String filename_rege = dto.getFilename_regex();
                Integer delay_tolerance = dto.getDelay_tolerance();
                Integer permitted_watch = dto.getPermittedWatch();
                String data_kind = dto.getDataKind();
                String data_type = dto.getDataType();
                receiveSettingRepository.receiveSettingModify(time_zone, filename_pattern, filename_rege, delay_tolerance, permitted_watch, data_kind, data_type);
                result++;
            }
        } catch (Exception e) {
            System.out.println("insert error : " +e);
        }
        return result;
    }

    // 문자 메시지 패턴 일괄 수정
    public Integer smsSetMsgModify(List<SmsSendPatternDto> smsSendPatternDto){
        Integer result = 0;
        try {
            for( SmsSendPatternDto dto : smsSendPatternDto){
                String pattern = dto.getPattern();
                String activation = dto.getActivation();
                String code = dto.getCode();
                String mode = dto.getMode();
                smsSendPatternRepository.smsSetMsgModify(pattern, activation, code, mode);
                result++;
            }
        } catch (Exception e) {
            System.out.println("insert error : " +e);
        }
        return result;
    }

    // 지점별 운영상태 설정 일괄 수정
    public Integer stationStatusModify(List<StationStatusDto> stationStatusDto){
        Integer result = 0;
        try {
            for( StationStatusDto dto : stationStatusDto){
                String site_cd = dto.getSiteCd();
                String site_status = dto.getSiteStatus();
                stationStatusRepository.stationStatusModify(site_cd, site_status);
                result++;
            }
        } catch (Exception e) {
            System.out.println("insert error : " +e);
        }
        return result;
    }
    
}
