package kr.or.kimsn.radarsms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kr.or.kimsn.radarsms.dto.SmsSendDto;
import kr.or.kimsn.radarsms.dto.SmsSendOnOffDto;
import kr.or.kimsn.radarsms.repository.SmsSendOnOffRepository;
import kr.or.kimsn.radarsms.repository.SmsSendRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SmsService {
    
    private final SmsSendOnOffRepository smsSendOnOffRepository;
    private final SmsSendRepository smsSendRepository;

    // 문자 발송 대기 내역 totalcount
    public Integer getSmsAgentTotalCount (Integer yearMonth, String startDate, String endDate) {
        return smsSendRepository.getsmsSendTotalCount(yearMonth, startDate, endDate);
    }

    //문자 발송 대기 내역 page list
    // public List<SmsSendDto> getSmsSendData (Integer limitStart, Integer pageSize, Integer yearMonth, String startDate, String endDate) {
    public Page<SmsSendDto> getSmsSendData (Pageable pageable, Integer yearMonth, String startDate, String endDate) {
        // Pageable pageable = PageRequest.of(limitStart,10);
        // return smsSendRepository.getSmsSendData(limitStart, pageSize, yearMonth, startDate, endDate);
        return smsSendRepository.getSmsSendData(pageable, yearMonth, startDate, endDate);
    }
    
    //문자 발송기능 on/off 설정
    public List<SmsSendOnOffDto> getSmsSendOnOffData () {
        return smsSendOnOffRepository.findAll();
    }

    @Transactional
    public SmsSendOnOffDto updateOnOff(SmsSendOnOffDto smsSendOnOffDto ) throws Exception {
        try{
            smsSendOnOffDto = smsSendOnOffRepository.save(smsSendOnOffDto);
        }catch ( Exception e ){
            System.out.println("update error : "+e);
        }
        return smsSendOnOffDto;
    }

    //문자 발송
    public String smsSendsave(List<SmsSendDto> smsSendDto){
        // System.out.println("smsSendDto :::::: " + smsSendDto);
        String result = "";
        try {
            for(SmsSendDto dto : smsSendDto){
                dto.setCall_from("027337365");
                smsSendRepository.smsSendSave(dto.getReq_date(), dto.getCall_to(), dto.getCall_from(), dto.getSms_txt(), dto.getMsg_type());
            }
        } catch (Exception e) {
            result = "sms insert error : " + e;
            System.out.println("insert error : " +e);
        }
        return result;
    }
}
