package kr.or.kimsn.radarsms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.kimsn.radarsms.dto.SmsSendOnOffDto;
import kr.or.kimsn.radarsms.repository.SmsSendOnOffRepository;

@Service
public class SmsService {
    @Autowired
    private SmsSendOnOffRepository smsSendOnOffRepository; 
    
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
