package kr.or.kimsn.radarsms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.kimsn.radarsms.common.util.DateUtil;
import kr.or.kimsn.radarsms.dto.AppTemplateCodeDto;
import kr.or.kimsn.radarsms.dto.MenuDto;
import kr.or.kimsn.radarsms.dto.SmsSendDto;
import kr.or.kimsn.radarsms.dto.SmsSendOnOffDto;
import kr.or.kimsn.radarsms.dto.SmsTargetGroupDto;
import kr.or.kimsn.radarsms.dto.SmsTargetGroupMemberDto;
import kr.or.kimsn.radarsms.dto.StationDto;
import kr.or.kimsn.radarsms.service.ManageGetService;
import kr.or.kimsn.radarsms.service.MenuService;
import kr.or.kimsn.radarsms.service.SmsService;
import lombok.RequiredArgsConstructor;
/**
 * 문자발송
 */
@RequiredArgsConstructor
@Controller
public class SmsController {

    private final MenuService menuService;
    private final SmsService smsService;
    private final ManageGetService manageGetService;
    
    //문자 발송
    @GetMapping("/manage/sms_send")
    public String sms_send(@CookieValue(name = "userId", required = false) String userId, ModelMap model) {

        if(userId == null){
            return "views/login";
        }

        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time, last_check_time, sms_send, sms_send_activation
		// 				FROM watchdog.receive_condition; 
        List<StationDto> stationList = menuService.getStationList();
        map.put("menuList", menuList);
        map.put("stationList", stationList);

        model.addAttribute("list", map);

        Date today = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
    
        model.addAttribute("nowDate", date.format(today));
        model.addAttribute("nowTime", time.format(today));

        //문자 수신 그룹
        List<SmsTargetGroupDto> groups = manageGetService.getSmsTargetGroupList();
        model.addAttribute("groups", groups);

        //문자 수신 그룹 멤버
        List<SmsTargetGroupMemberDto> memberList = manageGetService.getSmsTargetGroupMemberList();
        model.addAttribute("memberList", memberList);

        //템플릿 코드정보
        List<AppTemplateCodeDto> tempCodeList = manageGetService.getAppTemplateCodeDtoList();
        model.addAttribute("tempCodeList", tempCodeList);

        return "views/manage/sms/sms_send";
    }

    //문자 발송 대기 내역
    @RequestMapping(value="/manage/sms_send_result", method = {RequestMethod.GET, RequestMethod.POST})
    public String sms_send_result(@CookieValue(name = "userId", required = false) String userId, 
                        ModelMap model, HttpServletRequest request, HttpServletResponse response, Pageable pageable) {
        
        if(userId == null){
            return "views/login";
        }
        
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time, last_check_time, sms_send, sms_send_activation
		// 				FROM watchdog.receive_condition; 
        List<StationDto> stationList = menuService.getStationList();
        map.put("menuList", menuList);
        map.put("stationList", stationList);
        
        model.addAttribute("list", map);

        int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber();
        System.out.println("pageable.getPageNumber() :::: " + pageable.getPageNumber());
        System.out.println("page :::: " + page);
        // pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "MSG_SEQ"));
        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "MSG_SEQ"));
        
        System.out.println("pageable ::::: "+ pageable);
        
        // parameter
        String sDt = request.getParameter("sDt");
       
        // 오늘 날짜
        Date now = new Date();
        Date dateStart = null;
        Date dateClose = null;

        String termStart = sDt != null ? sDt+"000000" : DateUtil.formatDate("yyyyMMdd", now)+"000000";
        String termClose = sDt != null ? sDt+"235959" : DateUtil.formatDate("yyyyMMdd", now)+"235959";

        dateStart = DateUtil.stringToDate("yyyyMMddHHmmss", termStart);
        dateClose = DateUtil.stringToDate("yyyyMMddHHmmss", termClose);

        if (dateStart == null) {
            dateStart = DateUtils.addDays(now, -1);
            // dateStart = now;
        }
        if (dateClose == null) {
            dateClose = now;
        }
        
        System.out.println("dateClose ::; " + dateClose);
        String searchDate = DateUtil.formatDate("yyyy'년 'MM'월 'dd'일'", dateClose);
        model.addAttribute("searchDate", searchDate);

        String yearMonth = DateUtil.formatDate("yyyyMM", dateClose);

        //total
        // Integer totalCnt = smsService.getSmsAgentTotalCount(Integer.parseInt(yearMonth), termStart, termClose);
        // model.addAttribute("totalCnt", totalCnt);

        // int pageNumber = Integer.parseInt(page); // 표시하려는 페이지 번호
        // int pageSize = 10; // 페이지 당 표시할 데이터 개수

        // // 페이지 번호와 페이지 사이즈를 이용하여 OFFSET과 LIMIT 값을 계산
        // int offset = (pageNumber - 1) * pageSize;

        //list
        // List<SmsSendDto> smsRsultList = smsService.getSmsSendData(offset, 10, Integer.parseInt(yearMonth), termStart, termClose);
        //문자발송 내역
        // Page<SmsSendDto> smsRsultList = smsService.getSmsSendData(pageable, Integer.parseInt(yearMonth), termStart, termClose);
        // model.addAttribute("smsRsultList", smsRsultList);

        //app 발송 내역
        Page<SmsSendDto> smsRsultList = smsService.getAppSendData(pageable, Integer.parseInt(yearMonth), termStart, termClose);
        model.addAttribute("smsRsultList", smsRsultList);

        return "views/manage/sms/sms_send_result";
    }

    //문자 발송 기능 ON/OFF 설정
    @GetMapping("/manage/sms_send_onoff")
    public String sms_send_onoff(@CookieValue(name = "userId", required = false) String userId, ModelMap model) {

        if(userId == null){
            return "views/login";
        }
        
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time, last_check_time, sms_send, sms_send_activation
		// 				FROM watchdog.receive_condition; 
        List<StationDto> stationList = menuService.getStationList();

        map.put("menuList", menuList);
        map.put("stationList", stationList);

        List<SmsSendOnOffDto> smsSendOnOffData = smsService.getSmsSendOnOffData();
        // System.out.println("smsSendOnOffData : " + smsSendOnOffData);
        model.addAttribute("onOffData", smsSendOnOffData);
        
        model.addAttribute("list", map);
        return "views/manage/sms/sms_send_onoff";
    }
}
