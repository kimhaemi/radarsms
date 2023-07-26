package kr.or.kimsn.radarsms.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import kr.or.kimsn.radarsms.dto.pkColumn.ReceiveConditionCriteriaPk;
import lombok.Data;

@Data
@Entity
@Table(name = "receive_condition_criteria")
@IdClass(ReceiveConditionCriteriaPk.class)
public class ReceiveConditionCriteriaDto {
    
    @Id
    private String code;
    private String name;
    private String criterion;
    private String comment;
    @Id
    private Integer gubun;
}
