package kr.or.kimsn.radarsms.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kr.or.kimsn.radarsms.dto.AppErrorCodeDto;
import kr.or.kimsn.radarsms.dto.AppTemplateCodeDto;
import kr.or.kimsn.radarsms.dto.SmsSendDto;
import kr.or.kimsn.radarsms.dto.SmsSendOnOffDto;
import kr.or.kimsn.radarsms.repository.AppErrorCodeRepository;
import kr.or.kimsn.radarsms.repository.AppTemplateCodeRepository;
import kr.or.kimsn.radarsms.repository.SmsSendOnOffRepository;
import kr.or.kimsn.radarsms.repository.SmsSendRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SmsService {

    private final SmsSendOnOffRepository smsSendOnOffRepository;
    private final SmsSendRepository smsSendRepository;
    private final AppTemplateCodeRepository appTemplateCodeRepository;

    private final AppErrorCodeRepository appErrorCodeRepository;

    // 문자 발송 내역 totalcount
    public Integer getSmsAgentTotalCount(Integer yearMonth, String startDate, String endDate) {
        return smsSendRepository.getsmsSendTotalCount(yearMonth, startDate, endDate);
    }

    // 문자 발송 내역 page list
    // public List<SmsSendDto> getSmsSendData (Integer limitStart, Integer pageSize,
    // Integer yearMonth, String startDate, String endDate) {
    public Page<SmsSendDto> getSmsSendData(Pageable pageable, Integer yearMonth, String startDate, String endDate) {
        // Pageable pageable = PageRequest.of(limitStart,10);
        // return smsSendRepository.getSmsSendData(limitStart, pageSize, yearMonth,
        // startDate, endDate);
        return smsSendRepository.getSmsSendData(pageable, yearMonth, startDate, endDate);
    }

    // App 발송 내역 page list
    public Page<SmsSendDto> getAppSendData(Pageable pageable, Integer yearMonth, String startDate, String endDate,
            String smsSUC, String smsFail) {
        // Pageable pageable = PageRequest.of(limitStart,10);
        // return smsSendRepository.getSmsSendData(limitStart, pageSize, yearMonth,
        // startDate, endDate);
        return smsSendRepository.getAppSendData(pageable, yearMonth, startDate, endDate, smsSUC, smsFail);
    }

    // app error code
    public List<AppErrorCodeDto> getAppErrorCode() {
        return appErrorCodeRepository.findAll();
    }

    // 문자 발송기능 on/off 설정 list
    public List<SmsSendOnOffDto> getSmsSendOnOffData() {
        return smsSendOnOffRepository.findAll();
    }

    // 문자 발송기능 on/off 설정
    @Transactional
    public SmsSendOnOffDto updateOnOff(SmsSendOnOffDto smsSendOnOffDto) throws Exception {
        try {
            smsSendOnOffDto = smsSendOnOffRepository.save(smsSendOnOffDto);
        } catch (Exception e) {
            log.info("update error : " + e);
        }
        return smsSendOnOffDto;
    }

    // 문자 발송
    public String smsSendsave(List<Map<String, Object>> dto) {
        log.info("dto :::::: " + dto);
        String result = "";

        // app content sequence
        Long appContentNextval = smsSendRepository.getAppContentNextval();
        log.info("appContentNextval ::::: " + appContentNextval);

        String smsText = dto.get(0).get("sms_txt").toString();

        // 카카오톡 발송(내용)
        Integer sms = smsSendRepository.gaonAppSendContentsSave(appContentNextval, smsText);
        log.info("카카오톡 발송(내용) insert : " + sms);

        try {
            for (Map<String, Object> smsDto : dto) {
                log.info("smsDto::::: " + smsDto);
                String call_from = "027337365";
                String call_to = smsDto.get("call_to").toString().replaceAll("-", "");
                String req_date = smsDto.get("req_date").toString().replace(".", "").replace(":", "");
                String templateCode = smsDto.get("templateCode").toString();

                // 카카오톡 발송(전화번호)
                Integer smstel = smsSendRepository.gaonAppSendDataSave(appContentNextval, req_date, call_to, call_from,
                        templateCode);
                log.info("카카오톡 발송(전화번호) insert : " + smstel);
            }

            // for(SmsSendDto dto : smsSendDto){
            // dto.setCall_from("027337365");
            // dto.setCall_to(dto.getCall_to().replaceAll("-", ""));
            // dto.setReq_date(dto.getReq_date().replace(".", "").replace(":", "")+"00");

            // // 문자발송
            // // smsSendRepository.nuriSmsSendSave(dto.getReq_date(), dto.getCall_to(),
            // dto.getCall_from(), dto.getSms_txt(), dto.getMsg_type());

            // //카카오톡 발송(전화번호)
            // smsSendRepository.gaonAppSendDataSave(Long.parseLong(appContentNextval),
            // dto.getReq_date(), dto.getCall_to(), dto.getCall_from(), templateCode);
            // }
        } catch (Exception e) {
            result = "sms insert error : " + e;
            log.info("insert error : " + e);
        }
        return result;
    }

    // template 등록
    @Transactional
    public Integer setAppTemplateCodeAdd(AppTemplateCodeDto dto) {
        Integer result = 0;
        try {
            log.info("사용자 등록 userAdd");
            result = appTemplateCodeRepository.setAppTemplateCodeAdd(dto.getTemplateCode(), dto.getHead());
        } catch (Exception e) {
            log.info("insert error : " + e);
            return -1;
        }
        return result;
    }

    // template 수정
    @Transactional
    public Integer setAppTemplateCodeModify(List<Map<String, Object>> appTemplateCodeDto) {
        Integer result = 0;

        log.info("appTemplateCodeDto :::; " + appTemplateCodeDto);

        try {

            for (Map<String, Object> smsDto : appTemplateCodeDto) {

                String oldTemplateCode = smsDto.get("oldTemplateCode").toString();
                String newTemplateCode = smsDto.get("newTemplateCode").toString();
                String head = smsDto.get("head").toString();
                String useButton = smsDto.get("useButton").toString();
                log.info("사용자 수정 usermodify");
                result = appTemplateCodeRepository.setAppTemplateCodeModify(newTemplateCode, oldTemplateCode, head,
                        useButton);
            }

        } catch (Exception e) {
            log.info("update error : " + e);
            return -1;
        }
        return result;
    }

    // template 삭제
    @Transactional
    public Integer setAppTemplateCodeDelete(String templateCode) {
        Integer result = 0;
        try {
            log.info("사용자 삭제 userDelete");
            result = appTemplateCodeRepository.setAppTemplateCodeDelete(templateCode);
        } catch (Exception e) {
            log.info("delete error : " + e);
            return -1;
        }
        return result;
    }

}
