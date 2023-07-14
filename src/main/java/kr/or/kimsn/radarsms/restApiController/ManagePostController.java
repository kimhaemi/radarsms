package kr.or.kimsn.radarsms.restApiController;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kimsn.radarsms.common.ApiResult;
import kr.or.kimsn.radarsms.dto.SmsSetRcDto;
import kr.or.kimsn.radarsms.service.ManageService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ManagePostController {

    private final ManageService manageService;

    @PostMapping("/manage/station_set_rc_update")
    public ApiResult<Integer> receiveConditionUpdate(@RequestBody List<SmsSetRcDto> req){
        return ApiResult.success(manageService.receiveConditionModify(req));
    }
}
