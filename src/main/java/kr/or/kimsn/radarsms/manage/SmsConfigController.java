package kr.or.kimsn.radarsms.manage;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * 설정
 */
@Controller
public class SmsConfigController {

    // @Autowired
    // private HistoricalDataService historicalDataService;

    //지점/자료별 문자 발송 설정
    @GetMapping("/manage/sms_set_rc")
    public String sms_set_rc(){
        return "views/manage/smsConfig/sms_set_rc";
    }

    //경고 기준 설정
    @GetMapping("/manage/sms_set_rcc")
    public String sms_set_rcc(){
        return "views/manage/smsConfig/sms_set_rcc";
    }

    //자료 수신 감시 설정
    @GetMapping("/manage/sms_set_rs")
    public String sms_set_rs(){
        return "views/manage/smsConfig/sms_set_rs";
    }

    //문자 메시지 패턴
    @GetMapping("/manage/sms_set_msg")
    public String sms_set_msg(){
        return "views/manage/smsConfig/sms_set_msg";
    }

    //지점별 운영상태 설정
    @GetMapping("/manage/station_status")
    public String station_status(){
        return "views/manage/smsConfig/station_status";
    }
    
    //저장

    //수정

}
