package kr.or.kimsn.radarsms.restApiController;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kimsn.radarsms.common.ApiResult;
import kr.or.kimsn.radarsms.dto.ReceiveConditionCriteriaDto;
import kr.or.kimsn.radarsms.dto.ReceiveSettingDto;
import kr.or.kimsn.radarsms.dto.SmsSendPatternDto;
import kr.or.kimsn.radarsms.dto.SmsSetRcDto;
import kr.or.kimsn.radarsms.dto.StationStatusDto;
import kr.or.kimsn.radarsms.service.ManagePostService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ManagePostController {

    private final ManagePostService managePostService;

    // 지점/자료별 문자 발송 설정 일괄 수정
    @PostMapping("/manage/station_set_rc_modify")
    public ApiResult<Integer> receiveConditionModify(@RequestBody List<SmsSetRcDto> req){
        return ApiResult.success(managePostService.receiveConditionModify(req));
    }

    // 경고 기준 설정 일괄 수정
    @PostMapping("/manage/station_set_rcc_modify")
    public ApiResult<Integer> receiveConditionCriteriaModify(@RequestBody List<ReceiveConditionCriteriaDto> req){
        return ApiResult.success(managePostService.receiveConditionCriteriaModify(req));
    }

    //자료 수신 감시 설정 일괄 수정
    @PostMapping("/manage/station_set_rs_modify")
    public ApiResult<Integer> receiveSettingModify(@RequestBody List<ReceiveSettingDto> req){
        return ApiResult.success(managePostService.receiveSettingModify(req));
    }

    // 문자 메시지 패턴 일괄 수정
    @PostMapping("/manage/sms_set_msg_modify")
    public ApiResult<Integer> smsSetMsgModify(@RequestBody List<SmsSendPatternDto> req){
        return ApiResult.success(managePostService.smsSetMsgModify(req));
    }

    // 지점별 운영상태 설정 일괄 수정
    @PostMapping("/manage/station_status_modify")
    public ApiResult<Integer> stationStatusModify(@RequestBody List<StationStatusDto> req){
        return ApiResult.success(managePostService.stationStatusModify(req));
    }
}
