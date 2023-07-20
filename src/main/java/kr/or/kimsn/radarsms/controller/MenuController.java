package kr.or.kimsn.radarsms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.kimsn.radarsms.common.util.DateUtil;
import kr.or.kimsn.radarsms.dto.MenuDto;
import kr.or.kimsn.radarsms.dto.ReceiveConditionJoinStationRdrDto;
import kr.or.kimsn.radarsms.dto.StationDto;
import kr.or.kimsn.radarsms.service.MenuService;
import lombok.RequiredArgsConstructor;

/**
 * 메뉴
 */
@RequiredArgsConstructor
@Controller
public class MenuController {
    
    private final MenuService menuService;

    //메인 화면(전체 감시)
    @GetMapping({"", "/"})
    public String main(ModelMap model) {
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        List<StationDto> stationList = menuService.getStationList();

        map.put("menuList", menuList);
        map.put("stationList", stationList);

        model.addAttribute("list", map);

        // 최종수신상태
        List<ReceiveConditionJoinStationRdrDto> rcState = menuService.getReceiveConditionJoinStationRdrList(stationList);
        model.addAttribute("rcState", rcState);

        Date now = new Date();
        DateUtil.formatDate("yyyy/MM/dd HH:mm:ss", now);

        
        return "views/main/main";
    }
}
