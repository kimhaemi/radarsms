package kr.or.kimsn.radarsms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.kimsn.radarsms.dto.MembersDto;
import kr.or.kimsn.radarsms.dto.MenuDto;
import kr.or.kimsn.radarsms.dto.StationDto;
import kr.or.kimsn.radarsms.service.MembersService;
import kr.or.kimsn.radarsms.service.MenuService;
import lombok.RequiredArgsConstructor;

/**
 * 사용자
 */
@RequiredArgsConstructor
@Controller
public class MembersController {
    private final MenuService menuService;
    private final MembersService membersService;

    // 사용자 등록 화면
    @GetMapping("/users/joinForm")
    public String join(@CookieValue(name = "userId", required = false) String userId) {
        if(userId == null){
            return "views/login";
        }
        return "views/users/joinForm";
    }

    // 사용자 관리
    @GetMapping("/users/admin_user")
    public String admin_user(@CookieValue(name = "userId", required = false) String userId, ModelMap model){

        if(userId == null){
            return "views/login";
        }

        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        List<StationDto> stationList = menuService.getStationList();
        map.put("menuList", menuList);
        map.put("stationList", stationList);

        List<MembersDto> membersList = membersService.getUsersList();
        map.put("membersList", membersList);
        
        model.addAttribute("list", map);
        
        return "views/users/admin_user";
    }

    // 사용자 등록 화면
    @GetMapping("/users/admin_user_form")
    public String admin_user_form(@CookieValue(name = "userId", required = false) String userId, ModelMap model, Long id) {

        if(userId == null){
            return "views/login";
        }
        
        System.out.println("id ::: " + id);
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        List<StationDto> stationList = menuService.getStationList();
        map.put("menuList", menuList);
        map.put("stationList", stationList);

        MembersDto memberData = null;

        if( id != null ){
            memberData = membersService.getUsersData(id);
            System.out.println("memberData :::; " + memberData);
        }

        map.put("memberData", memberData);
        
        model.addAttribute("list", map);
        
        return "views/users/admin_user_form";
    }
}
