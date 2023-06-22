package kr.or.kimsn.radarsms.restApiController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kimsn.radarsms.dto.MenuDto;
import kr.or.kimsn.radarsms.dto.SmsSendOnOffDto;
import kr.or.kimsn.radarsms.dto.StationDto;
import kr.or.kimsn.radarsms.service.MenuService;

@RestController
public class ApiController {

    @Autowired
    private MenuService menuService;
    
    @GetMapping("getMenuData")
    public Map<String, Object> menuData(){
        Map<String, Object> map = new HashMap<>();
        List<MenuDto> menuList = menuService.getMenuList();
        // List<StationDto> stationList = menuService.getStationList();

        map.put("menuList", menuList);
        // map.put("stationList", stationList);
        return map;
    }

    //메뉴 저장
    @GetMapping("/config/menu/menuSave")
    public Map<String, Object> menuSaveView(ModelMap model) {
        Map<String, Object> map = new HashMap<>();
        List<MenuDto> menuList = menuService.getMenuList();
        List<StationDto> stationList = menuService.getStationList();

        map.put("menuList", menuList);
        map.put("stationList", stationList);

        return map;
    }

    //메뉴 저장
    @PostMapping("/menu/save")
    public String menuSave(MenuDto menuDto, ModelMap model) throws Exception{
        // System.out.println(menuDto.getMenu1());
        // System.out.println(menuDto.getMenu2());
        // System.out.println(menuDto.getMenu3());
        menuDto = menuService.addMenuDto(menuDto);
        // menuDto = menuRepository.saveAndFlush(menuDto);
        System.out.println("menuDto");
        System.out.println(menuDto);
        return "redirect:/config/menu/menuSave";
    }

    //문자 발송 기능 ON/OFF 설정 저장
    // @GetMapping("/manage/sms_send_onoff/save")
    // public String smsSendOnOffSave(SmsSendOnOffDto smsSendOnOffDto) {

    //     return "redirect:/manage/sms_send_onoff";
    // }
}
