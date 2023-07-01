package kr.or.kimsn.radarsms.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import kr.or.kimsn.radarsms.dto.pkColumn.SmsSetRcPk;
import lombok.Data;

@Data
@Entity
@IdClass(SmsSetRcPk.class)
public class SmsSetRcDto {

    @Id
    private String dataKind;
    @Id
    private String site;
    @Id
    private String dataType;
    private String name_kr;
    private String data_name;
    private Integer sms_send_activation;

}