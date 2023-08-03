package kr.or.kimsn.radarsms.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.or.kimsn.radarsms.dto.SmsSendDto;

public interface SmsSendRepository extends JpaRepository<SmsSendDto, Long>{

    @Query(
        nativeQuery = true,
        value=
		"SELECT count(*) as cnt \n" + 
		"FROM (\n" + 
		"  SELECT\n" + 
		"    'MT'        AS MSG\n" + 
		"    -- ,CASE WHEN MSG_TYPE='4' THEN 'SMS' ELSE CASE WHEN MSG_TYPE2 IS NOT NULL THEN  MSG_TYPE2 ELSE 'LMS' END END  AS MSG_TYPE1\n" + 
		"    ,MSG_TYPE   AS MSG_TYPE\n" + 
		"    ,CALL_FROM  AS CALLBACK\n" + 
		"    ,CALL_TO    AS RECIVER\n" + 
		"    ,CASE WHEN RSLT_NET='SKT' THEN 'SK' ELSE CASE WHEN  RSLT_NET='KT' THEN 'KT' ELSE CASE WHEN RSLT_NET='LGU' THEN 'LG' ELSE '' END END END AS TELCO -- 이통사\n" + 
		"    ,CUR_STATE     AS CUR_STATE\n" + 
		"    ,RSLT_CODE2    AS RSLT_CODE2\n" + 
		"    ,CASE WHEN MSG_TYPE=4 THEN SMS_TXT\n" + 
		"    ELSE\n" + 
		"    ( SELECT CONCAT(CASE WHEN MMS_SUBJECT IS NOT NULL THEN MMS_SUBJECT ELSE''END, '', MMS_BODY) AS MSG_TXT FROM nuri.NURI_MMS_CONTENTS_INFO mms WHERE mms.CONT_SEQ=msg.CONT_SEQ )\n" + 
		"    END AS MSG_TXT\n" + 
		"    ,CASE WHEN MSG_TYPE=4 THEN length(SMS_TXT)\n" + 
		"    ELSE\n" + 
		"    ( SELECT length(MMS_BODY) AS MSG_TXT  FROM nuri.NURI_MMS_CONTENTS_INFO mms WHERE mms.CONT_SEQ=msg.CONT_SEQ )\n" + 
		"    END AS MSG_BYTE\n" + 
		"    --          ,(CASE WHEN MSG_IDATE IS NULL THEN REQ_DATE ELSE MSG_IDATE END) AS MSG_IDATE                           -- MT 입력시간  MSG_IDATE 입력시간\n" + 
		"    ,REQ_DATE   AS REQ_DATE                                                                                -- MT 요청시간  REQ_DATE\n" + 
		"    ,SENT_DATE  AS SENT_DATE                                                                               -- MT 발송시간  SENT_DATE(접수)\n" + 
		"    ,RSLT_DATE  AS RSLT_DATE                                                                               -- MT 결과수신  RSLT_DATE(결과수신)\n" + 
		"    ,MSG_SEQ    AS MSG_KEY                                                                                 -- KEY\n" + 
		"    ,CONT_SEQ   AS CONT_SEQ                                                                                -- MT: 첨부파일참조용 텍스트 조회용 ,MO는 빈값\n" + 
		"    -- ********************************************************************** --\n" + 
		"    -- 가온에 별도 시퀀스 또는 별도 시퀀스사용시\n" + 
		"    -- setting.yaml 파일\n" + 
		"    -- msgDataSeq: AUTO, 시퀀스\n" + 
		"    -- msgContentsInfoSeq: AUTO, 시퀀스\n" + 
		"    -- *********************** --\n" + 
		"    ,( SELECT APP_GUBUN     FROM nuri.app_send_data_log a where a.NURI_MSG_SEQ=msg.MSG_SEQ ) AS APP_CHK         -- APP 처리 후 실패인지 확인\n" + 
		"    ,( SELECT RSLT_CODE_APP FROM nuri.app_send_data_log a where a.NURI_MSG_SEQ=msg.MSG_SEQ ) AS RSLT_CODE_APP   -- APP 처리 후 실패인지 확인\n" + 
		"    -- ********************************************************************** --\n" + 
		"    ,''   AS TEMPLATE_CODE                                                                                 -- TEMPLATE_CODE : MT, MO는 빈값\n" + 
		"  FROM (\n" + 
		"    SELECT * FROM nuri.NURI_MSG_DATA\n" + 
		"    WHERE REQ_DATE between :startDate and :endDate \n" + 
		"    UNION ALL\n" + 
		"    SELECT * FROM nuri.NURI_MSG_LOG_:yearMonth\n" + 
		"    WHERE REQ_DATE between :startDate and :endDate \n" + 
		"  ) AS msg \n" + 
		") MT\n"
    )
    Integer getsmsSendTotalCount(
		@Param("yearMonth") Integer yearMonth,
		@Param("startDate") String startDate,
		@Param("endDate") String endDate
	);

	@Query(
        nativeQuery = true,
		countQuery = 
		"SELECT\n" + 
		"  count(*) \n" + 
		"FROM (\n" + 
		"  SELECT\n" + 
		"    'MT'        AS MSG\n" + 
		"    -- ,CASE WHEN MSG_TYPE='4' THEN 'SMS' ELSE CASE WHEN MSG_TYPE2 IS NOT NULL THEN  MSG_TYPE2 ELSE 'LMS' END END  AS MSG_TYPE1\n" + 
		"    ,MSG_TYPE   AS MSG_TYPE\n" + 
		"    ,CALL_FROM  AS CALLBACK\n" + 
		"    ,CALL_TO    AS RECIVER\n" + 
		"    ,CASE WHEN RSLT_NET='SKT' THEN 'SK' ELSE CASE WHEN  RSLT_NET='KT' THEN 'KT' ELSE CASE WHEN RSLT_NET='LGU' THEN 'LG' ELSE '' END END END AS TELCO -- 이통사\n" + 
		"    ,CUR_STATE     AS CUR_STATE\n" + 
		"    ,RSLT_CODE2    AS RSLT_CODE2\n" + 
		"    ,CASE WHEN MSG_TYPE=4 THEN SMS_TXT\n" + 
		"     ELSE \n" + 
		"       ( SELECT CONCAT(CASE WHEN MMS_SUBJECT IS NOT NULL THEN MMS_SUBJECT ELSE''END, '', MMS_BODY) AS MSG_TXT FROM nuri.NURI_MMS_CONTENTS_INFO mms WHERE mms.CONT_SEQ=msg.CONT_SEQ )\n" + 
		"     END AS MSG_TXT\n" + 
		"    ,CASE WHEN MSG_TYPE=4 THEN length(SMS_TXT)\n" + 
		"     ELSE \n" + 
		"      ( SELECT length(MMS_BODY) AS MSG_TXT  FROM nuri.NURI_MMS_CONTENTS_INFO mms WHERE mms.CONT_SEQ=msg.CONT_SEQ )\n" + 
		"     END AS MSG_BYTE\n" + 
		"    --          ,(CASE WHEN MSG_IDATE IS NULL THEN REQ_DATE ELSE MSG_IDATE END) AS MSG_IDATE                           -- MT 입력시간  MSG_IDATE 입력시간\n" + 
		"    ,REQ_DATE   AS REQ_DATE                                                                                -- MT 요청시간  REQ_DATE\n" + 
		"    ,SENT_DATE  AS SENT_DATE                                                                               -- MT 발송시간  SENT_DATE(접수)\n" + 
		"    ,RSLT_DATE  AS RSLT_DATE                                                                               -- MT 결과수신  RSLT_DATE(결과수신)\n" + 
		"    ,MSG_SEQ    AS MSG_KEY                                                                                 -- KEY\n" + 
		"    ,CONT_SEQ   AS CONT_SEQ                                                                                -- MT: 첨부파일참조용 텍스트 조회용 ,MO는 빈값\n" + 
		"    -- ********************************************************************** --\n" + 
		"    -- 가온에 별도 시퀀스 또는 별도 시퀀스사용시\n" + 
		"    -- setting.yaml 파일\n" + 
		"    -- msgDataSeq: AUTO, 시퀀스\n" + 
		"    -- msgContentsInfoSeq: AUTO, 시퀀스\n" + 
		"    -- *********************** --\n" + 
		"    ,( SELECT APP_GUBUN     FROM nuri.app_send_data_log a where a.NURI_MSG_SEQ=msg.MSG_SEQ ) AS APP_CHK         -- APP 처리 후 실패인지 확인\n" + 
		"    ,( SELECT RSLT_CODE_APP FROM nuri.app_send_data_log a where a.NURI_MSG_SEQ=msg.MSG_SEQ ) AS RSLT_CODE_APP   -- APP 처리 후 실패인지 확인\n" + 
		"    -- ********************************************************************** --\n" + 
		"    ,''   AS TEMPLATE_CODE                                                                                 -- TEMPLATE_CODE : MT, MO는 빈값\n" + 
		"  FROM (\n" + 
		"    SELECT * FROM nuri.NURI_MSG_DATA\n" + 
		"    WHERE REQ_DATE between :startDate and :endDate \n" + 
		"    UNION ALL\n" + 
		"    SELECT * FROM nuri.NURI_MSG_LOG_:yearMonth\n" + 
		"    WHERE REQ_DATE between :startDate and :endDate \n" + 
		"  ) AS msg \n" + 
		") MT\n"
		,
        value=
		"SELECT\n" + 
		"  MSG_KEY as msg_seq,\n" + 
		"  REQ_DATE as req_date,\n" + 
		"  CUR_STATE as cur_state,\n" + 
		"  RECIVER as call_to,\n" + 
		"  CALLBACK as call_from,\n" + 
		"  MSG_TXT as sms_txt,\n" + 
		"  MSG_TYPE as msg_type, \n" + 
		"  RSLT_CODE2 as rslt_code2 \n" + 
		"FROM (\n" + 
		"  SELECT\n" + 
		"    'MT'        AS MSG\n" + 
		"    -- ,CASE WHEN MSG_TYPE='4' THEN 'SMS' ELSE CASE WHEN MSG_TYPE2 IS NOT NULL THEN  MSG_TYPE2 ELSE 'LMS' END END  AS MSG_TYPE1\n" + 
		"    ,MSG_TYPE   AS MSG_TYPE\n" + 
		"    ,CALL_FROM  AS CALLBACK\n" + 
		"    ,CALL_TO    AS RECIVER\n" + 
		"    ,CASE WHEN RSLT_NET='SKT' THEN 'SK' ELSE CASE WHEN  RSLT_NET='KT' THEN 'KT' ELSE CASE WHEN RSLT_NET='LGU' THEN 'LG' ELSE '' END END END AS TELCO -- 이통사\n" + 
		"    ,CUR_STATE     AS CUR_STATE\n" + 
		"    ,RSLT_CODE2    AS RSLT_CODE2\n" + 
		"    ,CASE WHEN MSG_TYPE=4 THEN SMS_TXT\n" + 
		"     ELSE \n" + 
		"       ( SELECT CONCAT(CASE WHEN MMS_SUBJECT IS NOT NULL THEN MMS_SUBJECT ELSE''END, '', MMS_BODY) AS MSG_TXT FROM nuri.NURI_MMS_CONTENTS_INFO mms WHERE mms.CONT_SEQ=msg.CONT_SEQ )\n" + 
		"     END AS MSG_TXT\n" + 
		"    ,CASE WHEN MSG_TYPE=4 THEN length(SMS_TXT)\n" + 
		"     ELSE \n" + 
		"      ( SELECT length(MMS_BODY) AS MSG_TXT  FROM nuri.NURI_MMS_CONTENTS_INFO mms WHERE mms.CONT_SEQ=msg.CONT_SEQ )\n" + 
		"     END AS MSG_BYTE\n" + 
		"    --          ,(CASE WHEN MSG_IDATE IS NULL THEN REQ_DATE ELSE MSG_IDATE END) AS MSG_IDATE                           -- MT 입력시간  MSG_IDATE 입력시간\n" + 
		"    ,REQ_DATE   AS REQ_DATE                                                                                -- MT 요청시간  REQ_DATE\n" + 
		"    ,SENT_DATE  AS SENT_DATE                                                                               -- MT 발송시간  SENT_DATE(접수)\n" + 
		"    ,RSLT_DATE  AS RSLT_DATE                                                                               -- MT 결과수신  RSLT_DATE(결과수신)\n" + 
		"    ,MSG_SEQ    AS MSG_KEY                                                                                 -- KEY\n" + 
		"    ,CONT_SEQ   AS CONT_SEQ                                                                                -- MT: 첨부파일참조용 텍스트 조회용 ,MO는 빈값\n" + 
		"    -- ********************************************************************** --\n" + 
		"    -- 가온에 별도 시퀀스 또는 별도 시퀀스사용시\n" + 
		"    -- setting.yaml 파일\n" + 
		"    -- msgDataSeq: AUTO, 시퀀스\n" + 
		"    -- msgContentsInfoSeq: AUTO, 시퀀스\n" + 
		"    -- *********************** --\n" + 
		"    ,( SELECT APP_GUBUN     FROM nuri.app_send_data_log a where a.NURI_MSG_SEQ=msg.MSG_SEQ ) AS APP_CHK         -- APP 처리 후 실패인지 확인\n" + 
		"    ,( SELECT RSLT_CODE_APP FROM nuri.app_send_data_log a where a.NURI_MSG_SEQ=msg.MSG_SEQ ) AS RSLT_CODE_APP   -- APP 처리 후 실패인지 확인\n" + 
		"    -- ********************************************************************** --\n" + 
		"    ,''   AS TEMPLATE_CODE                                                                                 -- TEMPLATE_CODE : MT, MO는 빈값\n" + 
		"  FROM (\n" + 
		"    SELECT * FROM nuri.NURI_MSG_DATA\n" + 
		"    WHERE REQ_DATE between :startDate and :endDate \n" + 
		"    UNION ALL\n" + 
		"    SELECT * FROM nuri.NURI_MSG_LOG_:yearMonth\n" + 
		"    WHERE REQ_DATE between :startDate and :endDate \n" + 
		"  ) AS msg \n" + 
		") MT\n"
		// "ORDER BY REQ_DATE desc \n"
		// "limit :limitStart, :pageSize \n"
    )
	// List<SmsSendDto> getSmsSendData(
	Page<SmsSendDto> getSmsSendData(
		Pageable pageable,
		// @Param("limitStart") Integer limitStart,
		// @Param("pageSize") Integer pageSize,
		@Param("yearMonth") Integer yearMonth,
		@Param("startDate") String startDate,
		@Param("endDate") String endDate
	);

	@Query(
		nativeQuery = true,
		value=
		"INSERT INTO nuri.NURI_MSG_DATA ( \n" +
		"  MSG_SEQ, \n" +
		"  REQ_DATE, \n" +
		"  CUR_STATE, \n" +
		"  CALL_TO, \n" +
		"  CALL_FROM, \n" +
		"  SMS_TXT, \n" +
		"  MSG_TYPE \n" +
		") VALUES( \n" +
		"  nuri.nextval(), -- MSG_SEQ  \n" +
		"  STR_TO_DATE(:req_date, '%Y%m%d %H%i%s'), -- REQ_DATE \n" +
		"  0, -- CUR_STATE \n" +
		"  :call_to, -- CALL_TO \n" +
		"  :call_from, -- CALL_FROM \n" +
		"  :sms_txt, -- SMS_TXT \n" +
		"  :msg_type -- MSG_TYPE \n" +
		") \n"
	)
	@Transactional
    @Modifying
	Integer smsSendSave(
		@Param("req_date") String req_date,
		@Param("call_to") String call_to,
		@Param("call_from") String call_from,
		@Param("sms_txt") String sms_txt,
		@Param("msg_type") Integer msg_type
	);
}
