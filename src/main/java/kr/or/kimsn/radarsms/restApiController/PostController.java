package kr.or.kimsn.radarsms.restApiController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kimsn.radarsms.common.ApiResult;
import kr.or.kimsn.radarsms.dto.MenuDto;
import kr.or.kimsn.radarsms.dto.UsersDto;
import kr.or.kimsn.radarsms.service.MenuService;
import kr.or.kimsn.radarsms.service.UsersService;
import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
@RequiredArgsConstructor
@RestController
public class PostController {

    private final MenuService menuService;
    private final UsersService usersService;
    
    //메뉴 저장
    // @PostMapping("/menu/save")
    // public String menuSave(MenuDto menuDto, ModelMap model) throws Exception{
    //     // System.out.println(menuDto.getMenu1());
    //     // System.out.println(menuDto.getMenu2());
    //     // System.out.println(menuDto.getMenu3());
    //     menuDto = menuService.addMenuDto(menuDto);
    //     // menuDto = menuRepository.saveAndFlush(menuDto);
    //     System.out.println("menuDto");
    //     System.out.println(menuDto);
    //     return "redirect:/config/menu/menuSave";
    // }

    //문자 발송 기능 ON/OFF 설정 저장
    // @GetMapping("/manage/sms_send_onoff/save")
    // public String smsSendOnOffSave(SmsSendOnOffDto smsSendOnOffDto) {

    //     return "redirect:/manage/sms_send_onoff";
    // }

    // 사용자 등록
    @PostMapping("/users/admin_user_add")
    public ResponseEntity<UsersDto> userSave(UsersDto usersDto){
        System.out.println("사용자 등록 api");
        return ResponseEntity.ok(usersService.userAdd(usersDto));
    }

    // 사용자 수정
    @PostMapping("/users/admin_user_modify")
    public ResponseEntity<UsersDto> userModify(UsersDto usersDto){
        System.out.println("사용자 수정 api");
        return ResponseEntity.ok(usersService.userModify(usersDto));
    }

    // 사용자 삭제
    @PostMapping("/users/admin_user_delete")
    public String userDelete(Long userId){
        System.out.println("userId : " + userId);
        System.out.println("사용자 삭제 api");
        usersService.userDelete(userId);
        return "삭제완료";
    }
}
