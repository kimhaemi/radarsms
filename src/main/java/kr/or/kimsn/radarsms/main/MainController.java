package kr.or.kimsn.radarsms.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
/**
 * 메뉴
 */
@Controller
public class MainController {
    
    @Autowired
    private MainService menuService;

    //메인 화면(전체 감시)
    @GetMapping({"", "/"})
    public String main(ModelMap model) {
        //메뉴조회
        List<MainDto> menuList = menuService.getMenuList();
        System.out.println(menuList);
        model.addAttribute("menuList", menuList);
        return "views/main/main";
    }

    //메뉴 저장
    @GetMapping("/config/menu/menuSave")
    public String menuSaveView(ModelMap model) {
        //메뉴조회
        List<MainDto> menuList = menuService.getMenuList();
        System.out.println(menuList);
        model.addAttribute("menuList", menuList);
        return "views/config/menu/menuSave";
    }

    //메뉴 저장
    @PostMapping("/menu/save")
    public String menuSave(MainDto menuDto, ModelMap model) throws Exception{
        System.out.println(menuDto.getMenu1());
        System.out.println(menuDto.getMenu2());
        System.out.println(menuDto.getMenu3());
        menuDto = menuService.addMenuDto(menuDto);
        // menuDto = menuRepository.saveAndFlush(menuDto);
        System.out.println("menuDto");
        System.out.println(menuDto);
        return "redirect:/config/menu/menuSave";
    }
}
