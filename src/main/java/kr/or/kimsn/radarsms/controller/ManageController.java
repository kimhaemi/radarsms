package kr.or.kimsn.radarsms.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.kimsn.radarsms.dto.MenuDto;
import kr.or.kimsn.radarsms.dto.StationDto;
import kr.or.kimsn.radarsms.dto.StationStatusDto;
import kr.or.kimsn.radarsms.service.ManageService;
import kr.or.kimsn.radarsms.service.MenuService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ManageController {

    private final MenuService menuService;
    private final ManageService manageService;
    
    //지점별 운영상태 설정
    @GetMapping("/manage/station_status")
    public String station_status(ModelMap model){
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time, last_check_time, sms_send, sms_send_activation
		// 				FROM watchdog.receive_condition; 
        List<StationDto> stationList = menuService.getStationList();
        map.put("menuList", menuList);
        map.put("stationList", stationList);

        List<StationStatusDto> stationStatusList = manageService.getStationStatusList();
        map.put("stationStatusList", stationStatusList);
        
        model.addAttribute("list", map);
        return "views/manage/station_status";
    }
}
