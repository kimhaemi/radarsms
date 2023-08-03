package kr.or.kimsn.radarsms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.kimsn.radarsms.common.util.DateUtil;
import kr.or.kimsn.radarsms.dto.MembersDto;
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
    public String main(@SessionAttribute(name = "loginUser", required = false)MembersDto MembersDto, ModelMap model, HttpServletRequest req, HttpServletResponse res) {
        // HttpSession session = req.getSession();

        // if( session.getAttribute("loginUser") == null){
        //     return "views/login";
        // }

        // System.out.println("session.getAttribute(\"loginUser\") ::::: " + session.getAttribute("loginUser"));

        // model.addAttribute("loginUser", MembersDto);
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
