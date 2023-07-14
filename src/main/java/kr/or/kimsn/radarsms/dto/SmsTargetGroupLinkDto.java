package kr.or.kimsn.radarsms.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
// import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
// @Table(name = "sms_target_group_link")
public class SmsTargetGroupLinkDto {

    @Id
    private String site;
    private String data_kind;
    private String data_type;
    private String group_id;

    private String name_kr;
    private String data_name;
    
    
}
