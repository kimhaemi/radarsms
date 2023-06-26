package kr.or.kimsn.radarsms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String join() {
        return "views/users/joinForm";
    }

    // 사용자 관리
    @GetMapping("/users/admin_user")
    public String admin_user(ModelMap model){
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time, last_check_time, sms_send, sms_send_activation
		// 				FROM watchdog.receive_condition; 
        List<StationDto> stationList = menuService.getStationList();

        List<MembersDto> membersList = membersService.getUsersList();

        map.put("menuList", menuList);
        map.put("stationList", stationList);

        map.put("membersList", membersList);
        
        model.addAttribute("list", map);
        
        return "views/users/admin_user";
    }

    // 사용자 등록 화면
    @GetMapping("/users/admin_user_form")
    public String admin_user_form(ModelMap model, Long id) {

        System.out.println("id ::: " + id);
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time, last_check_time, sms_send, sms_send_activation
		// 				FROM watchdog.receive_condition; 
        List<StationDto> stationList = menuService.getStationList();

        MembersDto memberData = null;

        if( id != null ){
            memberData = membersService.getUsersData(id);
            System.out.println("memberData :::; " + memberData);
        }

        map.put("menuList", menuList);
        map.put("stationList", stationList);

        map.put("memberData", memberData);
        
        model.addAttribute("list", map);
        
        return "views/users/admin_user_form";
    }
}
