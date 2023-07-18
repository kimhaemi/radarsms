package kr.or.kimsn.radarsms.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import kr.or.kimsn.radarsms.dto.pkColumn.SmsTargetGroupLinkPk;
import lombok.Data;

@Data
@Entity
@Table(name = "sms_target_group_link")
@IdClass(SmsTargetGroupLinkPk.class)
public class SmsTargetGroupLinkDto {

    @Id
    private String site;
    @Id
    private String data_kind;
    @Id
    private String data_type;
    @Id
    private String group_id;
}
