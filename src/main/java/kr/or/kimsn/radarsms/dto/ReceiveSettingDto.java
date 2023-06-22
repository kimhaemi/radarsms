package kr.or.kimsn.radarsms.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "receive_setting_1")
public class ReceiveSettingDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String data_kind;
    private String data_type;
    private String data_name;
    private String time_zone;
    private String filename_pattern;
    private String filename_regex;
    private String recv_interval;
    private String delay_tolerance;
    private String permitted_watch;
    
}
