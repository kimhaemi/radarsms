package kr.or.kimsn.radarsms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReceiveDto {

    //Receive_Condition
    private String site;
    private String data_kind;
    private String data_type;
    private String recv_condition;
    private String apply_time;
    private String last_check_time;
    private String sms_send;
    private String sms_send_activation;

    //station_rdr
    private String site_cd;
    private String site_num;
    private String name_kr;
    private String name_en;
    private String height;
    private String max_range;
    private String gate_size;
    private String gates;
    private String rain_intensity;
    private String addr;
    private String model;
    private String install_date;
    private String prod_company;
    private String prod_country;
    private String permitted_watch;
    private String sort_order;
    // private String ip;
    // private String user_id;
    // private String pwd;

    //receive_setting
    // private String dataKind;
    // private String dataType;
    private String data_name;
    private String time_zone;
    private String filename_pattern;
    private String filename_regex;
    private String recv_interval;
    private String delay_tolerance;
    // private String permitted_watch;


    
}
