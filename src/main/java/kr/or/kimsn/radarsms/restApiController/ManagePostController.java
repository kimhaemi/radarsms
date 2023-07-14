package kr.or.kimsn.radarsms.restApiController;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kimsn.radarsms.common.ApiResult;
import kr.or.kimsn.radarsms.dto.ReceiveConditionCriteriaDto;
import kr.or.kimsn.radarsms.dto.SmsSetRcDto;
import kr.or.kimsn.radarsms.service.ManagePostService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ManagePostController {

    private final ManagePostService managePostService;

    @PostMapping("/manage/station_set_rc_modify")
    public ApiResult<Integer> receiveConditionModify(@RequestBody List<SmsSetRcDto> req){
        return ApiResult.success(managePostService.receiveConditionModify(req));
    }
    @PostMapping("/manage/station_set_rcc_modify")
    public ApiResult<Integer> receiveConditionCriteriaModify(@RequestBody List<ReceiveConditionCriteriaDto> req){
        return ApiResult.success(managePostService.receiveConditionCriteriaModify(req));
    }
}
