package kr.or.kimsn.radarsms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.kimsn.radarsms.dto.ReceiveConditionCriteriaDto;
import kr.or.kimsn.radarsms.dto.SmsSetRcDto;
import kr.or.kimsn.radarsms.repository.ReceiveConditionCriteriaRepository;
import kr.or.kimsn.radarsms.repository.ReceiveConditionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ManagePostService {

    private final ReceiveConditionRepository receiveConditionRepository;
    private final ReceiveConditionCriteriaRepository receiveConditionCriteriaRepository;

    //지점/자료별 문자 발송 설정 저장
    public Integer receiveConditionModify (List<SmsSetRcDto> req){
        Integer result = 0;
        try {
            for(SmsSetRcDto list : req){
                int sms_send_activation = list.getSms_send_activation();
                String data_kind = list.getDataKind();
                String site = list.getSite();
                String dataType = list.getDataType();
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

    public Integer receiveConditionCriteriaModify (List<ReceiveConditionCriteriaDto> receiveConditionCriteriaDto){
        Integer result = 0;
        try {
            for(ReceiveConditionCriteriaDto list : receiveConditionCriteriaDto){
                String code = list.getCode();
                String criterion = list.getCriterion();
                receiveConditionCriteriaRepository.receiveConditionCriteriaModify(criterion, code);
                result++;
            }
        } catch (Exception e) {
            System.out.println("insert error : " +e);
        }
        return result;
    }
    
}
