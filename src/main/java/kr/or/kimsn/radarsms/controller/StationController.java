package kr.or.kimsn.radarsms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.or.kimsn.radarsms.dto.MenuDto;
import kr.or.kimsn.radarsms.dto.StationDto;
import kr.or.kimsn.radarsms.service.MenuService;
import kr.or.kimsn.radarsms.service.StationService;
/**
 * 지점별 감시
 */
@Controller
public class StationController {

    // @Autowired
    // private HistoricalDataService historicalDataService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private StationService stationService;

    //조회
    @GetMapping("/station/{name}")
    public String getStation(@PathVariable("name") String name, ModelMap model){
        
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time, last_check_time, sms_send, sms_send_activation
		// 				FROM watchdog.receive_condition; 
        List<StationDto> stationList = menuService.getStationList();

        //지점별 감시
        List<StationDto> stationDtl = stationService.getStationDetail(name);

        map.put("menuList", menuList);
        map.put("stationList", stationList);
        map.put("stationDtl", stationDtl);
        
        model.addAttribute("list", map);
        
        return "views/station/station";
    }

    //저장

    //수정

}
