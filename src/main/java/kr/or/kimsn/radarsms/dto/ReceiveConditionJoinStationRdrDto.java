package kr.or.kimsn.radarsms.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import kr.or.kimsn.radarsms.dto.pkColumn.ReceiveConditionJoinStationRdrPk;
import lombok.Data;

@Data
@Entity
@IdClass(ReceiveConditionJoinStationRdrPk.class)
public class ReceiveConditionJoinStationRdrDto {

    @Id
    private String site;
    @Id
    private String data_kind;
    @Id
    private String data_type;
    private String recv_condition;
    private String apply_time;
    private String last_check_time;
    private String sms_send;
    private String sms_send_activation;
    private Integer status;
    private String name_kr;
	private Integer gubun;
	private Integer permitted_watch ;
    
}
