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
    @Column(name="data_kind") private String dataKind;
    @Column(name="data_type") private String dataType;
    private String data_name;
    private String time_zone;
    private String filename_pattern;
    private String filename_regex;
    private String recv_interval;
    private String delay_tolerance;
    private String permitted_watch;
    
}
