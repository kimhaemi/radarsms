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
import org.springframework.web.bind.annotation.PathVariable;
// import org.apache.commons.lang3.time.DateUtils;

import kr.or.kimsn.radarsms.dto.MenuDto;
import kr.or.kimsn.radarsms.dto.ReceiveConditionDto;
import kr.or.kimsn.radarsms.dto.ReceiveSettingDto;
import kr.or.kimsn.radarsms.dto.StationDto;
import kr.or.kimsn.radarsms.service.MenuService;
import kr.or.kimsn.radarsms.service.StationService;
import kr.or.kimsn.radarsms.util.DateUtil;
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

    // 지점별 감시
    @GetMapping("/station/{site}")
    public String getStation(@PathVariable("site") String site, ModelMap model) {

        // lgt :낙뢰
        // rdr : radar(대형)
        // sml : 스몰
        String dataKind = !site.equals("LGT") ? "RDR" : "LGT";

        // 자료 수신 처리 설정
        stationService.getReceiveSetting(dataKind);

        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time,
        // last_check_time, sms_send, sms_send_activation
        // FROM watchdog.receive_condition;
        List<StationDto> stationList = menuService.getStationList();

        map.put("menuList", menuList);
        map.put("stationList", stationList);

        // 지점별 감시
        List<StationDto> stationDtl = stationService.getStationDetail(site);
        map.put("stationDtl", stationDtl);

        // 최종 수신
        List<ReceiveConditionDto> stationLastCheck = stationService.getStationLastCheck(site, "NQC");
        map.put("stationLastCheck", stationLastCheck);

        // receive_condition

        model.addAttribute("list", map);

        return "views/station/station";
    }

    // 과거자료 검색
    @GetMapping("/station/hist/{site}")
    public String getStationHist(@PathVariable("site") String site, HttpServletRequest request,
            HttpServletResponse response, ModelMap model) {
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

        // parameter
        String termStart = request.getParameter("termStart");
        String termClose = request.getParameter("termClose");

        // 오늘 날짜
        Date now = new Date();
        Date dateStart = null;
        Date dateClose = null;

        dateStart = DateUtil.stringToDate("yyyyMMddHHmm", termStart);
        dateClose = DateUtil.stringToDate("yyyyMMddHHmm", termClose);

        if (dateStart == null) {
            // dateStart = DateUtils.addDays(now, -1);
            dateStart = now;
        }
        if (dateClose == null) {
            dateClose = now;
        }

        // model.addAttribute("recvAlt",
        // this.watchService.getPropertyMap(this.propertyService.getString("Web.RecvIconAlt")));
        // model.addAttribute("searchDate", DateUtil.formatDate("yyyy'년 'MM'월 'dd'일'",
        // dateClose));

        // //과거자료 검색
        // System.out.println("termStart ::: " + termStart);

        // 레이더
        if (!site.equals("LGT")) {
            // 지점별 감시
            List<StationDto> stationDtl = stationService.getStationDetail(site);
            model.addAttribute("siteName", stationDtl.get(0).getName_kr());

            List<ReceiveSettingDto> rdrSet = stationService.getReceiveSetting("RDR", 1);
            model.addAttribute("rdrSet", rdrSet);
            System.out.println("rdrSet :::: " + rdrSet);

            // List<ReceiveSettingVO> list = this.watchService.getReceiveSetting("RDR");

            // List<StationRdrVO> stationRdrList = this.watchService.getStationRdrList();

            // for (StationRdrVO vo : stationRdrList) {
            // if (vo.getSite_cd().equals(site)) {
            // model.addAttribute("site", vo);
            // }
            // }
            // model.addAttribute("stationRdrList", stationRdrList);
            // model.addAttribute("rdrSet", list);

            // model.addAttribute("recvData", this.watchService.getReceiveData("RDR", site,
            // list, dateStart, dateClose));

            return "views/station/stationHistory";
        }

        // 낙뢰

        return "views/station/stationHistory";
    }

    // 저장

    // 수정

}
