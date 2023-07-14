package kr.or.kimsn.radarsms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.kimsn.radarsms.dto.MenuDto;
import kr.or.kimsn.radarsms.dto.SmsAgentDto;
import kr.or.kimsn.radarsms.dto.SmsSendOnOffDto;
import kr.or.kimsn.radarsms.dto.SmsTargetGroupDto;
import kr.or.kimsn.radarsms.dto.SmsTargetGroupMemberDto;
import kr.or.kimsn.radarsms.dto.StationDto;
import kr.or.kimsn.radarsms.service.ManageGetService;
import kr.or.kimsn.radarsms.service.MenuService;
import kr.or.kimsn.radarsms.service.SmsService;
import lombok.RequiredArgsConstructor;
/**
 * 문자발송
 */
@RequiredArgsConstructor
@Controller
public class SmsController {

    private final MenuService menuService;
    private final SmsService smsService;
    private final ManageGetService manageGetService;
    
    //문자 발송
    @GetMapping("/manage/sms_send")
    public String sms_send(ModelMap model) {
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time, last_check_time, sms_send, sms_send_activation
		// 				FROM watchdog.receive_condition; 
        List<StationDto> stationList = menuService.getStationList();
        map.put("menuList", menuList);
        map.put("stationList", stationList);

        Date today = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat time = new SimpleDateFormat("hh:mm");
    
        map.put("nowDate", date.format(today));
        map.put("nowTime", time.format(today));

        //문자 수신 그룹
        List<SmsTargetGroupDto> groups = manageGetService.getSmsTargetGroupList();
        map.put("groups", groups);

        //문자 수신 그룹 멤버
        List<SmsTargetGroupMemberDto> memberList = manageGetService.getSmsTargetGroupMemberList();
        map.put("memberList", memberList);
        
        model.addAttribute("list", map);
        return "views/manage/sms/sms_send";
    }

    //문자 발송 대기 내역
    @GetMapping("/manage/sms_send_result")
    public String sms_send_result(ModelMap model) {
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time, last_check_time, sms_send, sms_send_activation
		// 				FROM watchdog.receive_condition; 
        List<StationDto> stationList = menuService.getStationList();
        map.put("menuList", menuList);
        map.put("stationList", stationList);

        Integer totalCnt = smsService.getSmsAgentTotalCount();
        System.out.println("totalCnt : " + totalCnt);

        List<SmsAgentDto> smsRsultList = smsService.getSmsAgentTotalCount(0L, 10L);
        System.out.println("smsRsultList : " + smsRsultList);

        map.put("smsRsultList", smsRsultList);
        
        model.addAttribute("list", map);
        return "views/manage/sms/sms_send_result";
    }

    //문자 발송 기능 ON/OFF 설정
    @GetMapping("/manage/sms_send_onoff")
    public String sms_send_onoff(ModelMap model) {
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time, last_check_time, sms_send, sms_send_activation
		// 				FROM watchdog.receive_condition; 
        List<StationDto> stationList = menuService.getStationList();

        map.put("menuList", menuList);
        map.put("stationList", stationList);

        List<SmsSendOnOffDto> smsSendOnOffData = smsService.getSmsSendOnOffData();
        System.out.println("smsSendOnOffData : " + smsSendOnOffData);
        map.put("onOffData", smsSendOnOffData);
        
        model.addAttribute("list", map);
        return "views/manage/sms/sms_send_onoff";
    }
}
