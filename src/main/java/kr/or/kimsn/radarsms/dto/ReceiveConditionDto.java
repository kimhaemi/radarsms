package kr.or.kimsn.radarsms.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "receive_condition_1")
public class ReceiveConditionDto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String site;
    private String data_kind;
    private String data_type;
    private String recv_condition;
    private String apply_time;
    private String last_check_time;
    private String sms_send;
    private String sms_send_activation;

}
