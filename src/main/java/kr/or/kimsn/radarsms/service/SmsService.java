package kr.or.kimsn.radarsms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import kr.or.kimsn.radarsms.dto.SmsAgentDto;
import kr.or.kimsn.radarsms.dto.SmsSendOnOffDto;
import kr.or.kimsn.radarsms.repository.SmsAgentRepository;
import kr.or.kimsn.radarsms.repository.SmsSendOnOffRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SmsService {
    
    private final SmsSendOnOffRepository smsSendOnOffRepository;
    private final SmsAgentRepository smsAgentRepository;

    //문자 발송
    // public List<SmsSendOnOffDto> getSmsSendOnOffData () {
    //     return smsSendOnOffRepository.findAll();
    // }

    //문자 발송 대기 내역 totalcount
    public Integer getSmsAgentTotalCount () {
        return smsAgentRepository.findByTotalCount();
    }

    //문자 발송 대기 내역 page list
    public List<SmsAgentDto> getSmsAgentTotalCount (Long limitStart, Long pageSize) {
        return smsAgentRepository.findByPage(limitStart, pageSize);
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
}
