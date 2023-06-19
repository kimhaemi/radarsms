package kr.or.kimsn.radarsms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.kimsn.radarsms.dto.MainDto;
import kr.or.kimsn.radarsms.dto.StationDto;
import kr.or.kimsn.radarsms.service.MainService;

/**
 * 메뉴
 */
@Controller
public class MainController {
    
    @Autowired
    private MainService menuService;

    //메인 화면(전체 감시)
    @GetMapping({"", "/"})
    public ModelAndView main(ModelMap model) {
        ModelAndView mav = new ModelAndView();

        Map<String, Object> map = new HashMap<>();

        List<MainDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time, last_check_time, sms_send, sms_send_activation
		// 				FROM watchdog.receive_condition; 
        List<StationDto> stationList = menuService.getStationList();

        map.put("menuList", menuList);
        map.put("stationList", stationList);

        mav.addObject("list", map);

        mav.setViewName("views/main/main");

        return mav;

        // //메뉴조회
        // Map<String, Object> map = new HashMap<>();
        // List<MainDto> menuList = menuService.getMenuList();
        // List<StationDto> stationList = menuService.getStationList();
        
        // System.out.println("menuList ::: " + menuList);

        // map.put("menuList", menuList);
        // map.put("stationList", stationList);
        
        // model.addAttribute("menuList", map);
        
        // return "views/main/main";
    }
}
