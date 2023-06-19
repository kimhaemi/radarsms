package kr.or.kimsn.radarsms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import kr.or.kimsn.radarsms.dto.MainDto;
import kr.or.kimsn.radarsms.dto.StationDto;
import kr.or.kimsn.radarsms.service.MainService;
/**
 * 지점별 감시
 */
@Controller
public class StationController {

    // @Autowired
    // private HistoricalDataService historicalDataService;

    @Autowired
    private MainService menuService;

    //조회
    @GetMapping("/station/{name}")
    public ModelAndView getStation(@PathVariable("name") String name ){
        ModelAndView mav = new ModelAndView();

        Map<String, Object> map = new HashMap<>();

        List<MainDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time, last_check_time, sms_send, sms_send_activation
		// 				FROM watchdog.receive_condition; 
        List<StationDto> stationList = menuService.getStationList();

        map.put("menuList", menuList);
        map.put("stationList", stationList);
        map.put("siteCd", name);
        map.put("siteName", "관악산");

        mav.addObject("list", map);
        mav.setViewName("views/station/station");
        
        return mav;
    }

    //저장

    //수정

}
