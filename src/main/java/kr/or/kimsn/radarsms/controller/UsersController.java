package kr.or.kimsn.radarsms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.kimsn.radarsms.dto.MenuDto;
import kr.or.kimsn.radarsms.dto.StationDto;
import kr.or.kimsn.radarsms.dto.UsersDto;
import kr.or.kimsn.radarsms.repository.UsersRepository;
import kr.or.kimsn.radarsms.service.MenuService;
import kr.or.kimsn.radarsms.service.UsersService;
import lombok.RequiredArgsConstructor;
/**
 * 사용자
 */
@RequiredArgsConstructor
@Controller
public class UsersController {

    private final MenuService menuService;

    private final UsersService usersService;

    // @Autowired
    // private BCryptPasswordEncoder bCryptPasswordEncoder;

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

        List<UsersDto> usersList = usersService.getUsersList();

        map.put("menuList", menuList);
        map.put("stationList", stationList);

        map.put("usersList", usersList);
        
        model.addAttribute("list", map);
        
        return "views/users/admin_user";
    }

    // 사용자 등록 화면
    @GetMapping("/users/admin_user_form")
    public String admin_user_form(ModelMap model) {

        System.out.println("dfsdfsdfsdsf");
        Map<String, Object> map = new HashMap<>();

        List<MenuDto> menuList = menuService.getMenuList();
        // table join 필요
        // SELECT site, data_kind, data_type, recv_condition, apply_time, last_check_time, sms_send, sms_send_activation
		// 				FROM watchdog.receive_condition; 
        List<StationDto> stationList = menuService.getStationList();

        map.put("menuList", menuList);
        map.put("stationList", stationList);
        
        model.addAttribute("list", map);
        
        return "views/users/admin_user_form";
    }

    // 사용자 등록
    /* @PostMapping("/join")
    public String join(UserDto user) {
        System.out.println(user);
        // 비밀번호 암호화
        String rawPwd = user.getPassword();
        String encPwd = bCryptPasswordEncoder.encode(rawPwd);
        user.setPassword(encPwd);
        // 임의로 넣어줌.
        user.setRoles("ROLE_USER");
        userReository.save(user);
        return "redirect:/login";
    } */

    @GetMapping("/joinProc")
    public @ResponseBody String joinProc() {
        return "가입 완료";
    }

    // 에러
    @GetMapping("/common/error")
    public String error() {
        System.out.println("errordfsdfsdfsd");
        return "views/common/error";
    }

    // @PostMapping("/loginProc")
    // public String loginProc(@RequestParam("username") String username,
    // @RequestParam("password") String password,
    // Model model) {
    // model.addAttribute("username", username);
    // model.addAttribute("password", password);
    // return "redirect:/";
    // }
}