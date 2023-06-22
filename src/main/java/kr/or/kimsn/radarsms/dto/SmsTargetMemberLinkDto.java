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
@Table(name="sms_target_member_link")
public class SmsTargetMemberLinkDto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gid")
    private Long id;

	private Long mid;
    private String noti;
    private String warn;
    private String tota;
    private String retr;
    private String sms;
}
