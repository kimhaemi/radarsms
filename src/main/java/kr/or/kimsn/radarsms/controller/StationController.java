package kr.or.kimsn.radarsms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.or.kimsn.radarsms.dto.MenuDto;
import kr.or.kimsn.radarsms.dto.ReceiveConditionDto;
import kr.or.kimsn.radarsms.dto.StationDto;
import kr.or.kimsn.radarsms.service.MenuService;
import kr.or.kimsn.radarsms.service.StationService;
import lombok.RequiredArgsConstructor;
/**
 * 지점별 감시
 */
@RequiredArgsConstructor
@Controller
public class StationController {

    // @Autowired
    // private HistoricalDataService historicalDataService;

    private final MenuService menuService;
    private final StationService stationService;

    //조회
    @GetMapping("/station/{site}")
    public String getStation(@PathVariable("site") String site, ModelMap model){

        //lgt :낙뢰
        //rdr : radar(대형)
        //sml : 스몰
        String dataKind = !site.equals("LGT") ? "RDR" : "LGT";

        //자료 수신 처리 설정
        stationService.getReceiveSetting(dataKind);
        
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time, last_check_time, sms_send, sms_send_activation
		// 				FROM watchdog.receive_condition; 
        List<StationDto> stationList = menuService.getStationList();

        map.put("menuList", menuList);
        map.put("stationList", stationList);
        
        //지점별 감시
        List<StationDto> stationDtl = stationService.getStationDetail(site);
        map.put("stationDtl", stationDtl);

        //최종 수신
        List<ReceiveConditionDto> stationLastCheck = stationService.getStationLastCheck(site, "NQC");
        map.put("stationLastCheck", stationLastCheck);


        
        model.addAttribute("list", map);
        
        return "views/station/station";
    }

    //저장

    //수정

}
