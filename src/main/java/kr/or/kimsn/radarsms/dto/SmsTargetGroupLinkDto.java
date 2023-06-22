package kr.or.kimsn.radarsms.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "sms_target_group_link_1")
public class SmsTargetGroupLinkDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String site;
    @Column(name = "data_kind") private String dataKind;
    @Column(name = "data_type") private String dataType;
    @Column(name = "group_id")  private String groupId;
    
}
