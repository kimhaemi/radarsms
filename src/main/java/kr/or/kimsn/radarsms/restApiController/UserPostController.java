package kr.or.kimsn.radarsms.restApiController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kimsn.radarsms.common.ApiResult;
import kr.or.kimsn.radarsms.dto.MembersDto;
import kr.or.kimsn.radarsms.service.MembersService;
// import kr.or.kimsn.radarsms.service.MenuService;
// import kr.or.kimsn.radarsms.service.UsersService;
import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
@RequiredArgsConstructor
@RestController
public class UserPostController {

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
    @PostMapping("/users/admin_user_create")
    public ApiResult<MembersDto> userSave(@RequestBody MembersDto membersDto){
        return ApiResult.success(membersService.userAdd(membersDto));
    }

    // 사용자 수정
    @PostMapping("/users/admin_user_modify")
    public ApiResult<MembersDto> userModify(@RequestBody MembersDto membersDto){
        return ApiResult.success(membersService.userModify(membersDto));
    }

    // 사용자 삭제
    @PostMapping("/users/admin_user_del")
    public ApiResult<Long> userDelete(@RequestParam Long id){
        return ApiResult.success(membersService.userDelete(id));
    }
}
