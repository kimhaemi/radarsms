package kr.or.kimsn.radarsms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.kimsn.radarsms.dto.MenuDto;
import kr.or.kimsn.radarsms.dto.ReceiveConditionCriteriaDto;
import kr.or.kimsn.radarsms.dto.ReceiveSettingDto;
import kr.or.kimsn.radarsms.dto.SmsSendPatternDto;
import kr.or.kimsn.radarsms.dto.SmsSetRcDto;
import kr.or.kimsn.radarsms.dto.StationDto;
import kr.or.kimsn.radarsms.service.ManageService;
import kr.or.kimsn.radarsms.service.MenuService;
import lombok.RequiredArgsConstructor;
/**
 * 설정
 */
@RequiredArgsConstructor
@Controller
public class SmsConfigController {

    private final MenuService menuService;
    private final ManageService manageService;
    // private final historicalDataService historicalDataService;

    //지점/자료별 문자 발송 설정
    @GetMapping("/manage/sms_set_rc")
    public String sms_set_rc(ModelMap model){
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time, last_check_time, sms_send, sms_send_activation
		// 				FROM watchdog.receive_condition; 
        List<StationDto> stationList = menuService.getStationList();
        map.put("menuList", menuList);
        map.put("stationList", stationList);

        //자료 수신 상태
        List<SmsSetRcDto> receiveList =  manageService.getReceiveConditionList();
        System.out.println("receiveList : " + receiveList);
        map.put("receiveList", receiveList);
        
        model.addAttribute("list", map);

        return "views/manage/smsConfig/sms_set_rc";
    }

    //경고 기준 설정
    @GetMapping("/manage/sms_set_rcc")
    public String sms_set_rcc(ModelMap model){
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time, last_check_time, sms_send, sms_send_activation
		// 				FROM watchdog.receive_condition; 
        List<StationDto> stationList = menuService.getStationList();
        map.put("menuList", menuList);
        map.put("stationList", stationList);

        //경고 기준
        List<ReceiveConditionCriteriaDto> criteriaList = manageService.getReceiveConditionCriteriaList();
        System.out.println("criteriaList ::: " + criteriaList);
        map.put("criteriaList", criteriaList);
        
        model.addAttribute("list", map);
        return "views/manage/smsConfig/sms_set_rcc";
    }

    //자료 수신 감시 설정
    @GetMapping("/manage/sms_set_rs")
    public String sms_set_rs(ModelMap model){
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time, last_check_time, sms_send, sms_send_activation
		// 				FROM watchdog.receive_condition; 
        List<StationDto> stationList = menuService.getStationList();
        map.put("menuList", menuList);
        map.put("stationList", stationList);

        List<ReceiveSettingDto> receiveSettingList = manageService.getReceiveSettingList();
        map.put("receiveSettingList", receiveSettingList);
        System.out.println("receiveSettingList : \n" + receiveSettingList);
        
        model.addAttribute("list", map);
        return "views/manage/smsConfig/sms_set_rs";
    }

    //문자 메시지 패턴
    @GetMapping("/manage/sms_set_msg")
    public String sms_set_msg(ModelMap model){
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time, last_check_time, sms_send, sms_send_activation
		// 				FROM watchdog.receive_condition; 
        List<StationDto> stationList = menuService.getStationList();
        map.put("menuList", menuList);
        map.put("stationList", stationList);

        List<SmsSendPatternDto> smsSendPatternList = manageService.getSmsSendPatternList();
        map.put("smsSendPatternList", smsSendPatternList);
        System.out.println("smsSendPatternList : \n" + smsSendPatternList);
        
        model.addAttribute("list", map);
        return "views/manage/smsConfig/sms_set_msg";
    }

    
    //저장

    //수정

}