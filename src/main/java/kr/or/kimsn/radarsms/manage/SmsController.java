package kr.or.kimsn.radarsms.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SmsController {
    //문자 발송
    @GetMapping("/manage/sms_send")
    public String sms_send(){
        return "views/manage/sms/sms_send";
    }

    //문자 발송 대기 내역
    @GetMapping("/manage/sms_send_result")
    public String sms_send_result(){
        return "views/manage/sms/sms_send_result";
    }

    //문자 발송 기능 ON/OFF 설정
    @GetMapping("/manage/sms_send_onoff")
    public String sms_send_onoff(){
        return "views/manage/sms/sms_send_onoff";
    }
}
