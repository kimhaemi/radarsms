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
import kr.or.kimsn.radarsms.dto.SmsTargetGroupLinkDto;
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
    public String sms_target_group(ModelMap model) {
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
        map.put("smsTargetGroupList", smsTargetGroupList);

        model.addAttribute("list", map);
        return "views/manage/smsGroup/sms_target_group";
    }

    // 문자 수신 그룹 관리 > [ ADMIN ] 그룹 멤버 관리
    @GetMapping("/manage/sms_target_group_member")
    public String sms_target_group_member(ModelMap model, Long id) {
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time,
        // last_check_time, sms_send, sms_send_activation
        // FROM watchdog.receive_condition;
        List<StationDto> stationList = menuService.getStationList();
        map.put("menuList", menuList);
        map.put("stationList", stationList);

        if( id != null ){
            //수신 그룹
            SmsTargetGroupDto group = manageGetService.getSmsTargetGroupId(id);
            map.put("group", group);
            map.put("groupId", group.getId());
            map.put("groupName", group.getName());

            //그룹에 속한 사용자
            List<SmsTargetGroupMemberDto> links = manageGetService.getSmsTargetGroupsMemberId(id);
            map.put("links", links);

            //그룹에 속하지 않은 멤버
            List<SmsTargetGroupMemberDto> notlinks = manageGetService.getSmsTargetGroupsMemberIdNot(links);
            map.put("notlinks", notlinks);
            System.out.println("notlinks ::: " + notlinks);

            //그룹에 속한 사용자
            // List<> links = manageService.getSmsTargetGroupsMember(id);
            // map.put("links", links);
            

//             List<SmsTargetMemberVO> links = this.manageService.getSmsTargetGroupsMember(groupid);
// /* 214 */       model.addAttribute("links", links);
// /*     */       
// /* 216 */       model.addAttribute("notlinks", this.manageService.getSmsTargetGroupsNotMembers(links));

            model.addAttribute("list", map);
            return "views/manage/smsGroup/sms_target_group_member";
        }

        model.addAttribute("list", map);
        return "redirect:/manage/sms_target_group";
        
    }

    // 그룹 관리 > [ [1그룹] 관악 백령 광덕 면봉 ] 그룹 감시 자료 설정
    @GetMapping("/manage/sms_target_group_link")
    public String sms_target_group_link(ModelMap model, Long id) {
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time,
        // last_check_time, sms_send, sms_send_activation
        // FROM watchdog.receive_condition;
        List<StationDto> stationList = menuService.getStationList();
        map.put("menuList", menuList);
        map.put("stationList", stationList);

        SmsTargetGroupDto smsTargetGroupId = manageGetService.getSmsTargetGroupId(id);
        map.put("smsTargetGroupId", smsTargetGroupId);
        //그룹명
        map.put("groupName", smsTargetGroupId.getName());
        map.put("groupId", smsTargetGroupId.getId());

        List<SmsTargetGroupLinkDto> links = manageGetService.getTableJoinAll(id);
        map.put("links", links);
        // System.out.println("links ::: " + links);
        List<ReceiveDto> notlinks = manageGetService.getSmsTargetGroupNotLink(links);
        map.put("notlinks", notlinks);
        System.out.println("notlinks ::: " + notlinks);

        model.addAttribute("list", map);
        return "views/manage/smsGroup/sms_target_group_link";
    }

    // 문자 수신자 관리
    @GetMapping("/manage/sms_target_member")
    public String sms_target_member(ModelMap model) {
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
        map.put("targetmembers", targetmembers);

        model.addAttribute("list", map);
        return "views/manage/smsGroup/sms_target_member";
    }

    // 상시 문자 수신 그룹 관리
    @GetMapping("/manage/sms_target_monitorgroup")
    public String sms_target_monitorgroup(ModelMap model) {
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
        return "views/manage/smsGroup/sms_target_monitorgroup";
    }
}
