package kr.or.kimsn.radarsms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.kimsn.radarsms.dto.MenuDto;
import kr.or.kimsn.radarsms.dto.ReceiveDto;
import kr.or.kimsn.radarsms.dto.SmsTargetGroupDto;
import kr.or.kimsn.radarsms.dto.SmsTargetGroupLinkListDto;
import kr.or.kimsn.radarsms.dto.SmsTargetGroupMemberDto;
import kr.or.kimsn.radarsms.dto.SmsTargetMemberDto;
import kr.or.kimsn.radarsms.dto.StationDto;
import kr.or.kimsn.radarsms.service.ManageGetService;
import kr.or.kimsn.radarsms.service.MenuService;
import lombok.RequiredArgsConstructor;

/**
 * 문자 수신 그룹
 */
@RequiredArgsConstructor
@Controller
public class SmsGroupController {

    private final MenuService menuService;
    private final ManageGetService manageGetService;

    // 문자 수신 그룹 관리
    @GetMapping("/manage/sms_target_group")
    public String smsTargetGroup(ModelMap model) {
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time,
        // last_check_time, sms_send, sms_send_activation
        // FROM watchdog.receive_condition;
        List<StationDto> stationList = menuService.getStationList();
        map.put("menuList", menuList);
        map.put("stationList", stationList);

        List<SmsTargetGroupDto> smsTargetGroupList = manageGetService.getSmsTargetGroupList();
        model.addAttribute("smsTargetGroupList", smsTargetGroupList);

        model.addAttribute("list", map);
        return "views/manage/smsGroup/sms_target_group";
    }

    // 문자 수신 그룹 관리 > [ *** ] 그룹 멤버 관리
    @GetMapping("/manage/sms_target_group_member")
    public String smsTargetGroupMember(ModelMap model, Long gid) {
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time,
        // last_check_time, sms_send, sms_send_activation
        // FROM watchdog.receive_condition;
        List<StationDto> stationList = menuService.getStationList();
        map.put("menuList", menuList);
        map.put("stationList", stationList);

        if( gid != null ){
            //수신 그룹
            SmsTargetGroupDto group = manageGetService.getSmsTargetGroupId(gid);
            model.addAttribute("group", group);

            //그룹에 속한 사용자
            List<SmsTargetGroupMemberDto> links = manageGetService.getSmsTargetGroupsMemberId(gid);
            model.addAttribute("links", links);

            //그룹에 속하지 않은 멤버
            List<SmsTargetMemberDto> notlinks = manageGetService.getSmsTargetGroupsMemberIdNot(gid);
            model.addAttribute("notlinks", notlinks);
            // System.out.println("notlinks ::: " + notlinks);

            model.addAttribute("list", map);
            return "views/manage/smsGroup/sms_target_group_member";
        }

        model.addAttribute("list", map);
        return "redirect:/manage/sms_target_group";
        
    }

    // 그룹 관리 > [ *** ] 그룹 감시 자료 설정
    @GetMapping("/manage/sms_target_group_link")
    public String smsTargetGroupLink(ModelMap model, Long gid) {
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time,
        // last_check_time, sms_send, sms_send_activation
        // FROM watchdog.receive_condition;
        List<StationDto> stationList = menuService.getStationList();
        map.put("menuList", menuList);
        map.put("stationList", stationList);

        SmsTargetGroupDto smsTargetGroup = manageGetService.getSmsTargetGroupId(gid);
        model.addAttribute("smsTargetGroup", smsTargetGroup);
        
        List<SmsTargetGroupLinkListDto> links = manageGetService.getTableJoinAll(gid);
        model.addAttribute("links", links);
        // System.out.println("links ::: " + links);
        List<ReceiveDto> notlinks = manageGetService.getSmsTargetGroupNotLink(links);
        model.addAttribute("notlinks", notlinks);
        
        model.addAttribute("list", map);
        return "views/manage/smsGroup/sms_target_group_link";
    }

    // 문자 수신자 관리
    @GetMapping("/manage/sms_target_member")
    public String smsTargetMember(ModelMap model) {
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time,
        // last_check_time, sms_send, sms_send_activation
        // FROM watchdog.receive_condition;
        List<StationDto> stationList = menuService.getStationList();
        map.put("menuList", menuList);
        map.put("stationList", stationList);

        List<SmsTargetMemberDto> targetmembers = manageGetService.getSmsTargetMemberList();
        model.addAttribute("targetmembers", targetmembers);

        model.addAttribute("list", map);
        return "views/manage/smsGroup/sms_target_member";
    }

    // 상시 문자 수신 그룹 관리
    @GetMapping("/manage/sms_target_monitorgroup")
    public String smsTargetMonitorgroup(ModelMap model) {
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time,
        // last_check_time, sms_send, sms_send_activation
        // FROM watchdog.receive_condition;
        List<StationDto> stationList = menuService.getStationList();

        map.put("menuList", menuList);
        map.put("stationList", stationList);

        model.addAttribute("list", map);

        //그룹에 속한 사용자
        List<SmsTargetGroupMemberDto> links = manageGetService.getSmsTargetGroupsMemberId(1L);
        model.addAttribute("links", links);

        //그룹에 속하지 않은 멤버
        List<SmsTargetMemberDto> notlinks = manageGetService.getSmsTargetGroupsMemberIdNot(1L);
        model.addAttribute("notlinks", notlinks);

        return "views/manage/smsGroup/sms_target_monitorgroup";
    }
}
