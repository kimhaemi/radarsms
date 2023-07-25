package kr.or.kimsn.radarsms.restApiController;

import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kimsn.radarsms.dto.MenuDto;
import kr.or.kimsn.radarsms.service.MenuService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class GetController {

    private MenuService menuService;
    
    //메뉴 리스트
    @GetMapping("/getMenuData")
    // public ModelMap menuData(ModelMap model){
    public List<MenuDto> menuData(ModelMap model){
        model.addAttribute("menuList", menuService.getMenuList());
        // Map<String, Object> map = new HashMap<>();
        // List<MenuDto> menuList = menuService.getMenuList();
        // List<StationDto> stationList = menuService.getStationList();

        // map.put("stationList", stationList);
        return menuService.getMenuList();
        // return model;
    }

    
}
