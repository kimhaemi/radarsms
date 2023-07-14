package kr.or.kimsn.radarsms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.kimsn.radarsms.dto.MenuDto;
import kr.or.kimsn.radarsms.dto.SmsSendPatternDto;
import kr.or.kimsn.radarsms.dto.StationDto;
import kr.or.kimsn.radarsms.service.ManageGetService;
import kr.or.kimsn.radarsms.service.MenuService;
import lombok.RequiredArgsConstructor;

/**
 * 설정
 */
@RequiredArgsConstructor
@Controller
public class SmsConfigController {

    private final MenuService menuService;
    private final ManageGetService manageGetService;
    // private final historicalDataService historicalDataService;

    // 문자 메시지 패턴
    @GetMapping("/manage/sms_set_msg")
    public String sms_set_msg(ModelMap model) {
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time,
        // last_check_time, sms_send, sms_send_activation
        // FROM watchdog.receive_condition;
        List<StationDto> stationList = menuService.getStationList();
        map.put("menuList", menuList);
        map.put("stationList", stationList);

        List<SmsSendPatternDto> smsSendPatternList = manageGetService.getSmsSendPatternList();
        map.put("smsSendPatternList", smsSendPatternList);
        System.out.println("smsSendPatternList : \n" + smsSendPatternList);

        model.addAttribute("list", map);
        return "views/manage/smsConfig/sms_set_msg";
    }

    // 저장

    // 수정

}