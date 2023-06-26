package kr.or.kimsn.radarsms.restApiController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kimsn.radarsms.dto.MenuDto;
import kr.or.kimsn.radarsms.service.MenuService;

@RestController
public class GetController {

    @Autowired
    private MenuService menuService;
    
    //메뉴 리스트
    @GetMapping("/getMenuData")
    public Map<String, Object> menuData(){
        Map<String, Object> map = new HashMap<>();
        List<MenuDto> menuList = menuService.getMenuList();
        // List<StationDto> stationList = menuService.getStationList();

        map.put("menuList", menuList);
        // map.put("stationList", stationList);
        return map;
    }

    
}
