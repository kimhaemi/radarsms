package kr.or.kimsn.radarsms.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "receive_setting")
public class ReceiveSettingDto {

    @Id
    @Column(name="data_type") private String dataType;
    @Column(name="data_kind") private String dataKind;
    private String data_name;
    private String time_zone;
    private String filename_pattern;
    private String filename_regex;
    private Integer recv_interval;
    private Integer delay_tolerance;
    @Column(name="permitted_watch") private Integer permittedWatch;
    
}
