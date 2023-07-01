package kr.or.kimsn.radarsms.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
// @Table(name = "sms_agent_mms")

public class SmsAgentDto {

    @Id
    private Long num;
    private String MSGKEY;
    private String PHONE;
    private Integer STATUS;
    private String REQDATE;
    private String MSG;
    private String TYPE;
    
}
