package kr.or.kimsn.radarsms.dto.pkColumn;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsSetRcPk implements Serializable{
    private String dataKind;
    private String site;
    private String dataType;
}
