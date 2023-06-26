package kr.or.kimsn.radarsms.restApiController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kimsn.radarsms.dto.MembersDto;
import kr.or.kimsn.radarsms.dto.UsersDto;
import kr.or.kimsn.radarsms.service.MembersService;
// import kr.or.kimsn.radarsms.service.MenuService;
// import kr.or.kimsn.radarsms.service.UsersService;
import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
@RequiredArgsConstructor
@RestController
public class PostController {

    // private final MenuService menuService;
    // private final UsersService usersService;
    private final MembersService membersService;
    
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
    // @PostMapping("/users/admin_user_add")
    // public ResponseEntity<UsersDto> userSave(UsersDto usersDto){
    //     System.out.println("사용자 등록 api");
    //     return ResponseEntity.ok(membersService.userAdd(usersDto));
    // }

    // 사용자 수정
    @PostMapping("/users/admin_user_modify")
    public ResponseEntity<MembersDto> userModify(){
        // System.out.println("id ::: " + id);
        // System.out.println("membersDto ::: " + membersDto);
        System.out.println("사용자 수정 api");
        // return ResponseEntity.ok(membersService.userModify(membersDto));
        return null;
    }

    // 사용자 삭제
    // @PostMapping("/users/admin_user_delete")
    // public String userDelete(Long userId){
    //     System.out.println("userId : " + userId);
    //     System.out.println("사용자 삭제 api");
    //     usersService.userDelete(userId);
    //     return "삭제완료";
    // }
}
