package kr.or.kimsn.radarsms.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import kr.or.kimsn.radarsms.dto.pkColumn.ReceivePk;
import lombok.Data;

@Data
@Entity
@IdClass(ReceivePk.class)
public class ReceiveDto {

    @Id
    private String data_kind;
    @Id
    private String data_type;
    @Id
    private String site;
    private String name_kr;
    private String data_name;
    private String sms_send_activation;
    
}
