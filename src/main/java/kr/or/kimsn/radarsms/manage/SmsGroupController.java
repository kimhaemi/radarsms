package kr.or.kimsn.radarsms.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SmsGroupController {
    //문자 수신 그룹 관리
    @GetMapping("/manage/sms_target_group")
    public String sms_target_group(){
        return "views/manage/smsGroup/sms_target_group";
    }

    //문자 수신 그룹 관리 > [ [1그룹] 관악 백령 광덕 면봉 ] 그룹 멤버 관리
    @GetMapping("/manage/sms_target_group_member")
    public String sms_target_group_member(){
        return "views/manage/smsGroup/sms_target_group_member";
    }

    //그룹 관리 > [ [1그룹] 관악 백령 광덕 면봉 ] 그룹 감시 자료 설정
    @GetMapping("/manage/sms_target_group_link")
    public String sms_target_group_link(){
        return "views/manage/smsGroup/sms_target_group_link";
    }

    //문자 수신자 관리
    @GetMapping("/manage/sms_target_member")
    public String sms_target_member(){
        return "views/manage/smsGroup/sms_target_member";
    }

    //상시 문자 수신 그룹 관리
    @GetMapping("/manage/sms_target_monitorgroup")
    public String sms_target_monitorgroup(){
        return "views/manage/smsGroup/sms_target_monitorgroup";
    }
}
